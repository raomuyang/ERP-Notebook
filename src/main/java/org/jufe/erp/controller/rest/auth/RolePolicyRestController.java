package org.jufe.erp.controller.rest.auth;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Policy;
import org.jufe.erp.entity.RolePolocy;
import org.jufe.erp.service.auth.RolePolicyService;
import org.jufe.erp.utils.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private Logger logger = Logger.getLogger(PolicyRestController.class);

    @RequestMapping("/get-all")
    public List<RolePolocy> getAll(){
        logger.debug("/get-all");
        return rolePolicyService.getAll();
    }

    @RequestMapping("/get")
    public RolePolocy get(String roleId, String policyId){
        logger.debug(String.format("/get role[%s], policyId[%s]", roleId, policyId));
        return rolePolicyService.get(roleId, policyId);
    }

    @RequestMapping("/get-policy-by-roleid")
    public List<Policy> getPolicyByRoleId(String roleId){
        logger.debug("/get-policy-by-roleid:" + roleId);
        return rolePolicyService.getPolicyByRoleId(roleId);
    }

    @RequestMapping("/get-valid-role-policy")
    public List<RolePolocy> getValidRolePolicy(String roleId, String date){
        logger.debug("/get-valid-role-policy:" + roleId + "," + date);
        Date dateObj = DateTools.string2Date(date);
        return rolePolicyService.getBeforeTermD(roleId, dateObj);
    }

    @RequestMapping("/get-valid-policy")
    public List<Policy> getValidPolicy(String roleId, String date){
        logger.debug("/get-valid-policy:" + roleId + "," + date);
        Date dateObj = DateTools.string2Date(date);
        return rolePolicyService.getPolicyBeforTermDate(roleId, dateObj);
    }


}
