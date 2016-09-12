package org.jufe.erp.service.photo.impl;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.PhotoWall;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.photo.PhotoWallRepository;
import org.jufe.erp.service.photo.PhotoWallService;
import org.jufe.erp.utils.FileUtils;
import org.jufe.erp.utils.MongoUtil;
import org.jufe.erp.utils.enums.ResourceEnum;
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

    private Logger logger = Logger.getLogger(PhotoWallServiceImpl.class);

    @Override
    public boolean addUserPhoto(PhotoWall photoWall, MultipartFile multipartFile, String rootPath) {
        try {
            String subpath = ResourceEnum.PHOTO.p() + "/" + photoWall.getGrade();
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileId = new ObjectId().toString();
            String path = rootPath + "/" + subpath;
            FileUtils.writeFile(path, fileId + suffix, multipartFile.getBytes());

            photoWall.setId(fileId);
            photoWall.setUrl("/" + subpath + "/" + fileId + suffix);
            return repository.insert(photoWall);
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
    public boolean updateUserName(String id, String userName) {
        return repository.updateUserName(id, userName);
    }

    @Override
    public boolean updateUserPhoto(String id, MultipartFile multipartFile, String rootPath) {
        try {
            PhotoWall photoWall = repository.findById(id);

            String subpath = ResourceEnum.PHOTO.p() + "/" + photoWall.getGrade();
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            String path = rootPath + "/" + subpath;
            File file = new File(path + "/" + id + suffix );
            if(file.exists())//暂时不删除上传的照片
                file.renameTo(new File(path + "/" + id + "_bak" + System.currentTimeMillis() + suffix));
            FileUtils.writeFile(path, id + suffix, multipartFile.getBytes());

            return true;
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
    public boolean deleteByPhotoId(String id, String rootPath) {
        PhotoWall photo = repository.findById(id);
        if (photo != null)
            try {
                String url = photo.getUrl();
                if(repository.deleteById(id)){
                    FileUtils.deleteFile(rootPath + url);
                    return true;
                }
            }catch (Exception e){
                logger.error("Delete By Id:" + e);
            }
        return false;
    }
}
