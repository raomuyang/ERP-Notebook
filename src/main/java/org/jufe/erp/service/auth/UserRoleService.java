package org.jufe.erp.service.auth;

import org.jufe.erp.entity.UserRole;
import org.jufe.erp.repository.BaseInterface;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface UserRoleService extends BaseInterface<UserRole> {
    public List<UserRole> findByUserId(String userId);

    public List<UserRole> findByRoleId(String roleId);

    /**
     * 查找用户有效的角色绑定
     * @param userId
     * @param termDate
     * @return
     */
    public List<UserRole> findBeforeTermD(String userId, Date termDate);
}
