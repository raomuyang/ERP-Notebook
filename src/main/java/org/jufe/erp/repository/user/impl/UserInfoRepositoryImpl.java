package org.jufe.erp.repository.user.impl;

import org.bson.types.ObjectId;
import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.user.UserInfoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
@Repository
public class UserInfoRepositoryImpl extends BaseRepository<UserInfo> implements UserInfoRepository{

    public UserInfo findByUserId(String userId){
        return super.findOne(new Query(new Criteria("userId").is(userId)));
    }

    /**
     * 只要userInfo对象中的id能顺利转为ObjectId，就基本不会有外部侵入的问题
     * @param userInfo
     * @return
     */
    public boolean update(UserInfo userInfo){
        try {
            ObjectId id = new ObjectId(userInfo.getId());
            return super.save(userInfo);//如果没有信息，则创建
        }catch (Exception e){
            logger.error(e.toString());
            return false;
        }

    }

    /**
     * 清除详细信息
     * 若出错，则返回的list为null
     * @param userId
     * @return
     */
    public List<UserInfo> deleteByUserId(String userId){
        return super.delete(new Query(new Criteria("userId").is(userId)));
    }

}
