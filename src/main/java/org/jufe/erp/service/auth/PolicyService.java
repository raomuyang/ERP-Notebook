package org.jufe.erp.service.auth;

import org.jufe.erp.entity.Policy;
import org.jufe.erp.repository.BaseInterface;

/**
 * Created by Raomengnan on 2016/9/1.
 */
public interface PolicyService{

    public Policy getPolicy(String id);
    public boolean savePolicy(Policy policy);
    public boolean deletePolicy(String id);
    public boolean setAuth(String id, boolean read, boolean write, boolean update, boolean delete);



}
