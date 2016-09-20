package org.jufe.erp.service.auth.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Policy;
import org.jufe.erp.entity.RolePolocy;
import org.jufe.erp.repository.auth.RolePolicyRepository;
import org.jufe.erp.service.auth.RolePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/2.
 */
@Service
public class RolePolicyServiceImpl implements RolePolicyService{
    @Autowired
    private RolePolicyRepository rolePolicyRepository;

    private Logger logger = Logger.getLogger(RolePolicyServiceImpl.class);

    @Override
    public List<RolePolocy> getAll() {
        return rolePolicyRepository.findAll();
    }

    @Override
    public RolePolocy get(String roleId, String policyId) {
        Query query = new Query(new Criteria("roleId").is(roleId).and("policyId").is(policyId));
        List<RolePolocy> re = rolePolicyRepository.find(query);
        if(re != null && re.size() != 0)
            return re.get(0);
        return null;
    }

    @Override
    public List<RolePolocy> getByRoleId(String roleId) {
        return rolePolicyRepository.findByRoleId(roleId);
    }

    @Override
    public List<Policy> getPolicyByRoleId(String roleId) {

        List<RolePolocy> rolePolocies = getByRoleId(roleId);
        return getPolicyFromRolePoliciys(rolePolocies);
    }

    /**
     * 获取有效的角色权限
     * @param roleId
     * @param termDate
     * @return
     */
    @Override
    public List<RolePolocy> getValidBeforeDate(String roleId, Date termDate) {
        return rolePolicyRepository.findBeforeTermD(roleId, termDate);
    }

    @Override
    public List<Policy> getValidPolicyBeforDate(String roleId, Date termDate) {
        List<RolePolocy> rolePolocies = getValidBeforeDate(roleId, termDate);
        return getPolicyFromRolePoliciys(rolePolocies);
    }

    private List<Policy> getPolicyFromRolePoliciys(List<RolePolocy> rolePolocies){

        List<String> policyIds = new ArrayList<>();
        if(rolePolocies.size() != 0)
            for(RolePolocy rolePolocy: rolePolocies)
                policyIds.add(rolePolocy.getPolicyId());
        return getPolicyWhereIdIn(policyIds);
    }


    @Override
    public boolean save(RolePolocy rolePolocy) {
        if(rolePolocy.getRoleId() == null || rolePolocy.getPolicyId() == null)
            return false;
        RolePolocy rp = get(rolePolocy.getRoleId(), rolePolocy.getPolicyId());
        if(rp == null)
            return rolePolicyRepository.save(rolePolocy);
        return rolePolicyRepository.save(rp);
    }


    @Override
    public boolean delete(String id) {
        return rolePolicyRepository.deleteById(id);
    }


    private List<Policy> getPolicyWhereIdIn(List<String> policyIds){

        MongoOperations mongoTemplate = rolePolicyRepository.getMongoTemplate();

        Criteria criteria = new Criteria("id").in(policyIds);
        Query query = new Query(criteria);
        try {
            List<Policy> policies = mongoTemplate.find(query, Policy.class);
            return policies;
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

}
