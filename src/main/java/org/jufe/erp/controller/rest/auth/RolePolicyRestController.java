package org.jufe.erp.controller.rest.auth;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Policy;
import org.jufe.erp.entity.RolePolocy;
import org.jufe.erp.service.auth.PolicyService;
import org.jufe.erp.service.auth.RolePolicyService;
import org.jufe.erp.service.auth.RoleService;
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
 */
@RestController
@RequestMapping("/rest/auth/role-policy")
public class RolePolicyRestController {
    @Autowired
    private RolePolicyService rolePolicyService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PolicyService policyService;

    private Logger logger = Logger.getLogger(PolicyRestController.class);

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping("/get-all")
    public List<RolePolocy> getAll(){
        logger.debug("/get-all");
        return rolePolicyService.getAll();
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping("/get/{roleId}/{policyId}")
    public RolePolocy get(@PathVariable("roleId") String roleId, @PathVariable("policyId") String policyId){
        logger.debug(String.format("/get role[%s], policyId[%s]", roleId, policyId));
        return rolePolicyService.get(roleId, policyId);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping("/get-policy-by-roleid/{roleId}")
    public List<Policy> getPolicyByRoleId(@PathVariable("roleId") String roleId){
        logger.debug("/get-policy-by-roleid:" + roleId);
        return rolePolicyService.getPolicyByRoleId(roleId);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping("/get-valid-role-policy/{roleId}/{date}")
    public List<RolePolocy> getValidRolePolicy(@PathVariable("roleId") String roleId, @PathVariable("date") String date){
        logger.debug("/get-valid-role-policy:" + roleId + "," + date);
        Date dateObj = DateTools.string2Date(date);
        return rolePolicyService.getValidBeforeDate(roleId, dateObj);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping("/get-valid-policies-before/{roleId}/{dateStr}")
    public List<Policy> getValidPolicy(@PathVariable("roleId") String roleId, @PathVariable("dateStr") String dateStr){
        logger.debug("/get-valid-policy:" + roleId + "," + dateStr);
        if(roleId == null || dateStr == null)
            return null;
        Date dateObj = DateTools.string2Date(dateStr);
        return rolePolicyService.getValidPolicyBeforDate(roleId, dateObj);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping("get-valid-policies/{roleId}")
    public List<Policy> getValidPolicy(@PathVariable("roleId") String roleId){
        logger.debug("get-valid-policies:" + roleId);

        if(roleId == null)
            return null;
        return rolePolicyService.getValidPolicyBeforDate(roleId, new Date(System.currentTimeMillis()));
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody RolePolocy rolePolocy){

        logger.debug("update role-policy:" + rolePolocy);

        ModelMap map = new ModelMap();
        boolean result = false;
        if(rolePolocy != null
                && roleService.getRoleById(rolePolocy.getRoleId()) != null
                && policyService.getPolicy(rolePolocy.getPolicyId()) != null){
            result = rolePolicyService.save(rolePolocy);
        }
        else
            map.put("msg", "参数错误:" + rolePolocy);
        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.ADMIN)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(String id){
        logger.debug("delete role-policy:" + id);

        ModelMap map = new ModelMap();
        if(id == null || id.isEmpty())
            map.put("msg", "参数错误");

        boolean result = rolePolicyService.delete(id);
        if(!result)
            map.put("msg", "删除失败，请重试");
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK );
    }



}
