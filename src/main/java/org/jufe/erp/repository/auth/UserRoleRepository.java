package org.jufe.erp.repository.auth;

import org.jufe.erp.entity.UserRole;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface UserRoleRepository extends BaseInterface<UserRole> {
    public List<UserRole> findByUserId(String userId);

    public List<UserRole> findByRoleId(String roleId);

    /**
     * 查找用户有效的角色绑定
     * @param userId
     * @param date
     * @return
     */
    public List<UserRole> findValidsInDate(String userId, Date date);
}
