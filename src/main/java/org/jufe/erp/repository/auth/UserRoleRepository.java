package org.jufe.erp.repository.auth;

import org.jufe.erp.entity.UserRole;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
@Repository
public class UserRoleRepository extends BaseRepository<UserRole>{
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
    public List<UserRole> findBeforeTermD(String userId, Date termDate){
        return super.find(new Query(new Criteria("roleId").is(userId).and("termD").lte(termDate)));
    }
}
