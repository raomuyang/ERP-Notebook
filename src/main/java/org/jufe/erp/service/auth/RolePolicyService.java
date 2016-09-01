package org.jufe.erp.service.auth;

import org.jufe.erp.entity.Policy;
import org.jufe.erp.entity.RolePolocy;
import org.jufe.erp.repository.BaseInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 * 绑定角色和授权关系
 */
public interface RolePolicyService  {

    public List<RolePolocy> getAll();

    public RolePolocy get(String roleId, String policyId);

    public List<RolePolocy> getByRoleId(String roleId);

    public List<Policy> getPolicyByRoleId(String roleId);
    /**
     * 查找角色有效的授权绑定
     * @param roleId
     * @param termDate
     * @return
     */
    public List<RolePolocy> getBeforeTermD(String roleId, Date termDate);

    public static boolean isValid(RolePolocy rolePolocy) {
        Date now = new Date(System.currentTimeMillis());
        return now.before(rolePolocy.getTermD());
    }
}
