package org.jufe.erp.controller.rest.auth;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Role;
import org.jufe.erp.entity.User;
import org.jufe.erp.entity.UserRole;
import org.jufe.erp.service.auth.RoleService;
import org.jufe.erp.service.auth.UserRoleService;
import org.jufe.erp.service.user.UserService;
import org.jufe.erp.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by raomengnan on 16-9-12.
 * 用户和角色间授权关系
 */
@RestController
@RequestMapping("/rest/auth/user-role")
public class UserRoleRestController {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(PolicyRestController.class);

    @RequestMapping("/get-by-userid")
    public List<UserRole> getByUserId(String userId){
        logger.debug("get-by-userid: " + userId);
        return userRoleService.getByUserId(userId);
    }

    @RequestMapping("/get-by-roleid")
    public List<UserRole> getByRoleId(String roleId){
        logger.debug("get-by-roleid: " + roleId);
        return userRoleService.getByRoleId(roleId);
    }

    @RequestMapping("/get-users-by-roleid")
    public List<User> getUsersByRoleId(String roleId){
        logger.debug("get-users-by-roleid:" + roleId);
        return userRoleService.getUsersByRole(roleId);
    }

    @RequestMapping("/get-roles-by-userid")
    public List<Role> getRolesByUser(String userId){
        logger.debug("/get-roles-by-userid: " + userId);
        return userRoleService.getRoleByUser(userId);
    }

    /**
     * @param userId
     * @return
     */
    @RequestMapping("/get-valid-roles-of-user")
    public List<Role> getValidRoles(String userId){
        logger.debug("/get-valid-roles-of-user:" + userId);
        return userRoleService.getValidRoles(userId);
    }


    @RequestMapping(value = "/get-valid-roles-in-target-date")
    public List<Role> getValidRolesInTargetDate(String userId, Date date){

        logger.debug("/get-valid-roles-in-target-date:"+userId + "," + date);
        return userRoleService.getValidRolesBeforeDate(userId, date);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody UserRole userRole){
        logger.debug("update: " + userRole);
        ModelMap map = new ModelMap();
        boolean result = false;

        //保证参数中的userId和roleId是合法的
        if(userRole != null &&
                (userService.findById(userRole.getUserId()) != null &&
                    roleService.getRoleById(userRole.getRoleId()) != null)){
            result = userRoleService.saveUserRole(userRole);
            if(!result)
                map.put("msg", "更新失败，请检查后重试");
        }
        else
            map.put("msg", "参数错误:" + userRole);
        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(@RequestBody String id){
        logger.debug("delete userRole: " + id);
        ModelMap map = new ModelMap();
        boolean result = false;
        result = userRoleService.delete(id);
        map.put("result", result);
        if(!result)
            map.put("msg", "参数错误:" + id);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

}
