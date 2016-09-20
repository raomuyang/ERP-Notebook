package org.jufe.erp.service.auth;

import org.jufe.erp.entity.Role;
import org.jufe.erp.entity.User;
import org.jufe.erp.entity.UserRole;
import org.jufe.erp.repository.BaseInterface;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface UserRoleService {
    public List<UserRole> getByUserId(String userId);

    public List<UserRole> getByRoleId(String roleId);

    public List<User> getUsersByRole(String roleId);

    public List<Role> getRoleByUser(String roleId);

    /**
     * 查找用户有效的角色绑定
     * @param userId
     * @param termDate
     * @return
     */
    public List<UserRole> getValidsBeforeDate(String userId, Date termDate);

    public List<Role> getValidRolesBeforeDate(String userId, Date termDate);

    public List<UserRole> getValids(String userId);

    public List<Role> getValidRoles(String userId);

    public boolean saveUserRole(UserRole userRole);

    public boolean delete(String id);
}
