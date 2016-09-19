package org.jufe.erp.service.auth.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Role;
import org.jufe.erp.entity.User;
import org.jufe.erp.entity.UserRole;
import org.jufe.erp.repository.auth.UserRoleRepository;
import org.jufe.erp.service.auth.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    private Logger logger = Logger.getLogger(UserRoleServiceImpl.class);

    @Override
    public List<UserRole> getByUserId(String userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public List<UserRole> getByRoleId(String roleId) {
        return userRoleRepository.findByRoleId(roleId);
    }

    @Override
    public List<User> getUsersByRole(String roleId) {
        List<UserRole> userRoles = getByRoleId(roleId);
        List<String> userIds = new ArrayList<>();
        if(userRoles != null)
            for (UserRole userRole: userRoles)
                userIds.add(userRole.getUserId());

        return getUserById(userIds);
    }

    @Override
    public List<Role> getRoleByUser(String roleId) {
        List<UserRole> userRoles = getByRoleId(roleId);
        List<String> roleIds = new ArrayList<>();
        if(userRoles != null)
            for (UserRole userRole: userRoles)
                roleIds.add(userRole.getRoleId());
        return getRoleById(roleIds);
    }

    @Override
    public List<UserRole> getValidsBeforeDate(String userId, Date termDate) {
        return userRoleRepository.findValidsBeforeTermD(userId, termDate);
    }

    @Override
    public List<Role> getValidRolesBeforeDate(String userId, Date termDate) {
        List<UserRole> userRoles = getValidsBeforeDate(userId, termDate);
        List<String> roleIds = new ArrayList<>();
        if(userRoles != null)
            for (UserRole userRole: userRoles)
                roleIds.add(userRole.getRoleId());
        return getRoleById(roleIds);
    }

    @Override
    public List<UserRole> getValids(String userId) {
        return userRoleRepository.findValidsBeforeTermD(userId, new Date(System.currentTimeMillis()) );
    }

    @Override
    public List<Role> getValidRoles(String userId) {
        List<UserRole> userRoles = getValids(userId);
        List<String> roleIds = new ArrayList<>();
        if(userRoles != null)
            for (UserRole userRole: userRoles)
                roleIds.add(userRole.getRoleId());
        return getRoleById(roleIds);
    }

    private  List<Role> getRoleById(List<String> ids) {
        MongoOperations mongoTemplate = userRoleRepository.getMongoTemplate();
        Query query = new Query(new Criteria("id").in(ids));
        try {
            return mongoTemplate.find(query, Role.class);
        }catch (Exception e){
            logger.error("getRoleById:" + e);
            return null;
        }
    }

    private List<User> getUserById(List<String> ids){
        MongoOperations mongoTemplate = userRoleRepository.getMongoTemplate();
        Query query = new Query(new Criteria("id").in(ids));
        try {
            return mongoTemplate.find(query, User.class);
        }catch (Exception e){
            logger.error("getRoleById:" + e);
            return null;
        }
    }


    @Override
    public boolean saveUserRole(UserRole userRole) {
        if(userRole == null || userRole.getRoleId() == null || userRole.getUserId() == null ||
                userRole.getRoleId().equals("") || userRole.getUserId().equals(""))
            return false;
        UserRole ur = userRoleRepository.find(
                new Query(new Criteria("userId").is(userRole.getUserId()).
                        and("roleId").is(userRole.getRoleId()))).get(0);
        if(ur == null)
            return userRoleRepository.save(userRole);

        ur.setTermD(userRole.getTermD());
        return userRoleRepository.save(ur);
    }

    @Override
    public boolean delete(String id) {
        return userRoleRepository.deleteById(id);
    }
}
