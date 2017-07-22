package org.jufe.erp.repository.photo;

import org.jufe.erp.entity.PhotoWall;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;

import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface PhotoWallRepository extends BaseInterface<PhotoWall> {
    List<PhotoWall> findByGrade(int grade);

    List<PhotoWall> findByUserNameLike(String userName);

    boolean updateUserName(String id, String userName);

    boolean updateUrl(String id, String url);

    /**
     * 按年级从低到高排列 例如 2016---2013
     *
     * @param pno
     * @param pSize
     * @return
     */
    Page<PhotoWall> findPage(int pno, int pSize);
}
