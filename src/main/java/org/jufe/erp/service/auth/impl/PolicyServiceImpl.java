package org.jufe.erp.service.auth.impl;

import org.jufe.erp.entity.Policy;
import org.jufe.erp.repository.auth.PolicyRepository;
import org.jufe.erp.service.auth.PolicyService;
import org.jufe.erp.utils.enums.AuthEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raomengnan on 2016/9/2.
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public Policy getPolicy(String id) {
        return policyRepository.findById(id);
    }

    @Override
    public boolean savePolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public boolean deletePolicy(String id) {
        return policyRepository.deleteById(id);
    }

    @Override
    public boolean setAuth(String id, boolean read, boolean write, boolean update, boolean delete) {
        Map<AuthEnum, Boolean> auth = new HashMap<>();
        auth.put(AuthEnum.READ, read);
        auth.put(AuthEnum.WRITE, write);
        auth.put(AuthEnum.UPDATE, update);
        auth.put(AuthEnum.DELETE, delete);

        return policyRepository.update(id, "auth", auth);

    }
}
