package org.jufe.erp.repository.user;

import org.jufe.erp.entity.User;
import org.jufe.erp.repository.BaseInterface;

import java.util.List;

/**
 * Created by raomengnan on 16-8-29.
 * Operation with DB about User
 */
public interface UserRepository extends BaseInterface<User> {

    User findById(String id);

    List<User> findByName(String name);

    List<User> findByGrade(int grade);

    List<User> findByLocation(String location);

    User findUserByIdAndPwd(String id, String pwd);

    boolean addUser(User user);

    boolean updateUserName(User user);

    boolean updateUserPwd(User user);

    boolean updateLocation(User user);

    /**
     * 更新全部信息时一定要检查用户ID是否属实
     *
     * @param user
     * @return
     */
    boolean update(User user);


}
