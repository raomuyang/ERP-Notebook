package org.jufe.erp.controller.rest.auth;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Role;
import org.jufe.erp.entity.User;
import org.jufe.erp.entity.UserRole;
import org.jufe.erp.service.auth.RoleService;
import org.jufe.erp.service.auth.UserRoleService;
import org.jufe.erp.service.user.UserService;
import org.jufe.erp.utils.DateTools;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.AuthLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @RequestMapping("/infos/userid/{userid}")
    public List<UserRole> getByUserId(@PathVariable("userid") String userId){
        logger.debug("Get UserRole by userid:" + userId);
        List l = userRoleService.getByUserId(userId);
        return userRoleService.getByUserId(userId);
    }

    @RequestMapping("/infos/roleid/{roleid}")
    public List<UserRole> getByRoleId(@PathVariable("roleid") String roleId){
        logger.debug("Get User-role by roleid: " + roleId);
        return userRoleService.getByRoleId(roleId);
    }

    @RequestMapping("/users/roleid/{roleid}")
    public List<User> getUsersByRoleId(@PathVariable("roleid") String roleId){
        logger.debug("Get users by roleid:" + roleId);
        return userRoleService.getUsersByRole(roleId);
    }

    @RequestMapping("/roles/userid/{userid}")
    public List<Role> getRolesByUser(@PathVariable("userid") String userId){
        logger.debug("Get roles by userid:" + userId);
        return userRoleService.getRoleByUser(userId);
    }

    /**
     * @param userId
     * @return
     */
    @RequestMapping("/roles/valid/userid/{userid}")
    public List<Role> getValidRoles(@PathVariable("userid") String userId){
        logger.debug("/get-valid-roles-of-user:" + userId);
        return userRoleService.getValidRoles(userId);
    }


    @RequestMapping(value = "/roles/valid/userid/{userid}/date/{date}")
    public List<Role> getValidRolesInTargetDate(@PathVariable("userid") String userId,@PathVariable("date") String dateStr){

        logger.debug("/get-valid-roles-in-target-date:"+userId + "," + dateStr);
        Date date = DateTools.string2Date(dateStr);
        if(date == null)
            return null;
        return userRoleService.getValidRolesBeforeDate(userId, date);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
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

    @AuthRequest(level = AuthLevel.ADMIN)
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
