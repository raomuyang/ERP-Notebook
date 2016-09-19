package org.jufe.erp.repository.auth.impl;

import org.jufe.erp.entity.RolePolocy;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.auth.RolePolicyRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 * 绑定角色和授权关系
 */
@Repository
public class RolePolicyRepositoryImpl extends BaseRepository<RolePolocy> implements RolePolicyRepository{
    public List<RolePolocy> findByRoleId(String roleId){
        return super.find(new Query(new Criteria("roleId").is(roleId)));
    }

    /**
     * 查找角色有效的授权绑定
     * @param roleId
     * @param termDate
     * @return
     */
    public List<RolePolocy> findBeforeTermD(String roleId, Date termDate){
        return super.find(new Query(new Criteria("roleId").is(roleId).and("termD").gte(termDate)));
    }
}
