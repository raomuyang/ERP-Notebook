package org.jufe.erp.service.auth;

import org.jufe.erp.entity.Role;
import org.jufe.erp.repository.BaseInterface;

import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface RoleService{

    public Role getRoleById(String id);

    public List<Role> getAllRole();

    public boolean addRole(Role role);

    public boolean updateRole(Role role);

    public boolean deleteRole(String id);

    public boolean update(String id, String key, Object value);
}
