package org.jufe.erp.repository.auth;

import org.jufe.erp.entity.RolePolocy;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 * 绑定角色和授权关系
 */
public interface RolePolicyRepository extends BaseInterface<RolePolocy> {
    public List<RolePolocy> findByRoleId(String roleId);
    /**
     * 查找角色有效的授权绑定
     * @param roleId
     * @param termDate
     * @return
     */
    public List<RolePolocy> findBeforeTermD(String roleId, Date termDate);
}
