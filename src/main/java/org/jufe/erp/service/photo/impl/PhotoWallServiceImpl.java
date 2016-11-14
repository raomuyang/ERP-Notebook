package org.jufe.erp.service.photo.impl;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.PhotoWall;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.QOSComponent;
import org.jufe.erp.repository.photo.PhotoWallRepository;
import org.jufe.erp.service.photo.PhotoWallService;
import org.jufe.erp.utils.FileUtils;
import org.jufe.erp.utils.MongoUtil;
import org.jufe.erp.utils.MultipartFileSave;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.jufe.erp.utils.qiniu.QiniuConfig;
import org.jufe.erp.utils.qiniu.QiniuQOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class PhotoWallServiceImpl implements PhotoWallService{

    @Autowired
    private PhotoWallRepository repository;
    @Autowired
    private QOSComponent qosComponent;

    private Logger logger = Logger.getLogger(PhotoWallServiceImpl.class);

    @Override
    public boolean addUserPhoto(PhotoWall photoInfo, MultipartFile multipartFile) {
        try {
            if (photoInfo == null) {
                photoInfo = new PhotoWall();
                photoInfo.setUserName("未设置");
            }

            String id= new ObjectId().toString();
            String fileId = photoInfo.getGrade() + "/" + id;
            String url = MultipartFileSave.save2Qiniu(
                        qosComponent.getQos(), multipartFile, ResourceEnum.PHOTO, fileId);
            if(url == null)
                return false;
            photoInfo.setId(id);
            photoInfo.setUrl(url);
            return repository.insert(photoInfo);
        } catch (Exception e){
            logger.error("Add UserPhoto to wall:" + e);
        }
        return false;
    }

    @Override
    public List<PhotoWall> getPhotosByGrade(int grade) {
        return repository.findByGrade(grade);
    }

    @Override
    public List<PhotoWall> getPhotosByUserNameLike(String userName) {
        return repository.findByUserNameLike(userName);
    }

    @Override
    public boolean updateUserInfo(PhotoWall userInfo) {
        if(userInfo == null)
            return false;
        PhotoWall uifo = repository.findById(userInfo.getId());
        if(uifo == null)
            return false;
        uifo.setGrade(userInfo.getGrade());
        uifo.setUserName(userInfo.getUserName());
        return repository.save(uifo);
    }

    @Override
    public boolean updateUserPhoto(String id, MultipartFile multipartFile) {
        try {
            PhotoWall photoWall = repository.findById(id);

            String fileId =  photoWall.getGrade() + "/" + id;
            String url = MultipartFileSave.save2Qiniu(
                    qosComponent.getQos(), multipartFile, ResourceEnum.PHOTO, fileId);
            if(url != null){
                photoWall.setUrl(url);
                return repository.save(photoWall);
            }

            return false;
//            String subpath = ResourceEnum.PHOTO.p() + "/" + photoWall.getGrade();
//            String originalFilename = multipartFile.getOriginalFilename();
//            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//
//            String path = rootPath + "/" + subpath;
//            File file = new File(path + "/" + id + suffix );
//            if(file.exists())//暂时不删除上传的照片
//                file.renameTo(new File(path + "/" + id + "_bak" + System.currentTimeMillis() + suffix));
//            FileUtils.writeFile(path, id + suffix, multipartFile.getBytes());

        } catch (Exception e){
            logger.error("Add UserPhoto to wall:" + e);
        }
        return false;
    }

    /**
     * 默认按照年级降序排列
     * @param pno
     * @param pSize
     * @return
     */
    @Override
    public Page<PhotoWall> getPage(int pno, int pSize) {
        return repository.findPage(pno, pSize);
    }

    @Override
    public List<PhotoWall> getAll() {
        return repository.findAll("grade", MongoUtil.DESC);
    }

    @Override
    public boolean deleteByPhotoId(String id) {
        PhotoWall photo = repository.findById(id);
        if (photo != null)
            try {
                String url = photo.getUrl();
                if(repository.deleteById(id)){
                    qosComponent.getQos().deleteFile(url);
                    return true;
                }
            }catch (Exception e){
                logger.error("Delete By Id:" + e);
            }
        return false;
    }

}
