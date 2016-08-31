package org.jufe.erp.repository.photo.impl;

import org.jufe.erp.entity.PhotoWall;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.photo.PhotoWallRepository;
import org.jufe.erp.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
@Repository
public class PhotoWallRepositoryImpl extends BaseRepository<PhotoWall> implements PhotoWallRepository{
    public List<PhotoWall> findByGrade(int grade){
        return super.find(new Query(new Criteria("grade").is(grade)));
    }

    public List<PhotoWall> findByUserNameLike(String userName){
        return super.find(new Query(MongoUtil.fuzzyCriteria("userName", userName)));
    }

    public boolean updateUserName(String id, String userName){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("userName", userName));
    }

    public boolean updateUrl(String id, String url){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("url", url));
    }

    /**
     * 按年级从低到高排列 例如 2016---2013
     * @param pno
     * @param pSize
     * @return
     */
    public Page<PhotoWall> findPage(int pno, int pSize){
        return super.findPage(MongoUtil.soryBy(new Query(), MongoUtil.DESC, "grade"), pno, pSize);
    }
}
