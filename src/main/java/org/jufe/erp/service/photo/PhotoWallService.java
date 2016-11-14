package org.jufe.erp.service.photo;

import org.jufe.erp.entity.PhotoWall;
import org.jufe.erp.repository.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface PhotoWallService {

    /**
     * 添加照片
     * @param photoInfo
     * @return
     */
    public boolean addUserPhoto(PhotoWall photoInfo, MultipartFile multipartFile);

    public List<PhotoWall> getPhotosByGrade(int grade);
    public List<PhotoWall> getPhotosByUserNameLike(String userName);

    public boolean updateUserInfo(PhotoWall photoInfo);

    public boolean updateUserPhoto(String id, MultipartFile multipartFile);

    /**
     * 按年级从低到高排列 例如 2016---2013
     * @param pno
     * @param pSize
     * @return
     */
    public Page<PhotoWall> getPage(int pno, int pSize);

    public List<PhotoWall> getAll();

    public boolean deleteByPhotoId(String id);

}
