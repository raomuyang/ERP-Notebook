package org.jufe.erp.repository.user;

import org.bson.types.ObjectId;
import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface UserInfoRepository extends BaseInterface<UserInfo> {

    public UserInfo findByUserId(String userId);

    public boolean update(UserInfo userInfo);

    /**
     * 清除详细信息
     * 若出错，则返回的list为null
     * @param userId
     * @return
     */
    public List<UserInfo> deleteByUserId(String userId);

}
