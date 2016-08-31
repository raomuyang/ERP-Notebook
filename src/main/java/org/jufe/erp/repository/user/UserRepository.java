package org.jufe.erp.repository.user;

import org.jufe.erp.entity.User;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raomengnan on 16-8-29.
 * Option with DB about User
 */
@Repository
public interface UserRepository extends BaseInterface<User> {

    public User findById(String id);

    public List<User> findByName(String name);
    public List<User> findByGrade(int grade);
    public List<User> findByLocation(String location);

    public User findUserByIdAndPwd(String id, String pwd);

    public boolean addUser(User user);

    public boolean updateUserName(User user);

    public boolean updateUserPwd(User user);

    public boolean updateLocation(User user);

    /**
     * 更新全部信息时一定要检查用户ID是否属实
     * @param user
     * @return
     */
    public boolean update(User user);


}
