package org.jufe.erp.repository.auth.impl;

import org.jufe.erp.entity.UserRole;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.auth.UserRoleRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
@Repository
public class UserRoleRepositoryImpl extends BaseRepository<UserRole> implements UserRoleRepository{
    public List<UserRole> findByUserId(String userId){
        return super.find(new Query(new Criteria("userId").is(userId)));
    }

    public List<UserRole> findByRoleId(String roleId){
        return super.find(new Query(new Criteria("roleId").is(roleId)));
    }

    /**
     * 查找用户有效的角色绑定
     * @param userId
     * @param termDate
     * @return
     */
    public List<UserRole> findValidsBeforeTermD(String userId, Date termDate){
        //截止日期比参数日期更大
        return super.find(new Query(new Criteria("roleId").is(userId).and("termD").gte(termDate)));
    }
}
