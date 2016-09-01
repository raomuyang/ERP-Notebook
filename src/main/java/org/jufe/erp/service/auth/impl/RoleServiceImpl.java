package org.jufe.erp.service.auth.impl;

import org.jufe.erp.entity.Role;
import org.jufe.erp.repository.auth.RoleRepository;
import org.jufe.erp.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Raomengnan on 2016/9/2.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public boolean addRole(Role role) {
        return roleRepository.insert(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean deleteRole(String id) {
        return roleRepository.deleteById(id);
    }

    @Override
    public boolean update(String id, String key, Object value) {
        return roleRepository.update(id, key, value);
    }
}
