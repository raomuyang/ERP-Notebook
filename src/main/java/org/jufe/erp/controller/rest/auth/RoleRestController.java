package org.jufe.erp.controller.rest.auth;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Role;
import org.jufe.erp.service.auth.RoleService;
import org.jufe.erp.utils.JsonUtils;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.AuthLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by raomengnan on 16-9-12.
 */
@RestController
@RequestMapping("/rest/auth/role")
public class RoleRestController {
    @Autowired
    private RoleService roleService;
    private Logger logger = Logger.getLogger(PolicyRestController.class);

    @RequestMapping("get-all")
    public List<Role> getAll(){
        logger.debug("/get-all");
        return roleService.getAllRole();
    }

    @RequestMapping("/get/{roleId}")
    public Role getById(@PathVariable("roleId") String id){
        logger.debug("/get:" + id);
        return roleService.getRoleById(id);
    }

    /**
     * 获取多个role的值,可能需要使用，未测试post方法
     * @param args
     * @return
     */
    @RequestMapping("/get-roles/{roleIds}")
    public List<Role> getByIds(@PathVariable("roleIds") String args){
        logger.debug("/get-roles:" + args);
        List ids = JsonUtils.jsonToList(args);
        return roleService.getRoleById(ids);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/add-role", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> addRole(@RequestBody Role role){
        logger.debug("/add-role: " + role);
        ModelMap map = new ModelMap();
        boolean res = false;
        if(role != null){
            res = roleService.addRole(role);
            map.put("id", role.getId());
        }
        map.put("result", res);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/update-role", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody Role role){
        logger.debug("/update-role: " + role);
        ModelMap map = new ModelMap();
        boolean res = false;
        if(role != null){
            res = roleService.updateRole(role);
            map.put("id", role.getId());
        }
        map.put("result", res);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    /**
     * 更新角色部分信息
     * @param role
     * @return
     */
    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/update-role-info", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateInfo(@RequestBody Role role){
        logger.debug("/update-role-info: " + role);
        ModelMap map = new ModelMap();
        boolean res = false;
        if(role != null && role.getId() != null){
            Role originRole = roleService.getRoleById(role.getId());
            if(originRole != null){
                if(role.getRoleDes() != null)
                    originRole.setRoleDes(role.getRoleDes());
                if(role.getRoleName() != null)
                    originRole.setRoleName(role.getRoleName());
                res = roleService.updateRole(role);
                map.put("id", role.getId());
            }
        }
        map.put("result", res);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(@RequestBody String id){
        logger.debug("/delete:" + id);
        boolean res = false;
        ModelMap map = new ModelMap();
        if(id != null)
            res = roleService.deleteRole(id);
        map.put("result", res);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }



}
