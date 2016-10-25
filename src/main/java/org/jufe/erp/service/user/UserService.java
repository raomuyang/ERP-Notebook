package org.jufe.erp.service.user;

import org.jufe.erp.entity.User;
import org.jufe.erp.repository.BaseInterface;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by raomengnan on 16-8-29.
 * Operation with DB about User
 */
@Repository
public interface UserService {

    public User findById(String id);

    public List<User> findByName(String name);
    public List<User> findByGrade(int grade);
    public List<User> findByLocation(String location);

    public User checkLogin(String id, String pwd);

    public boolean addUser(User user, MultipartFile multipartFile, String rootPath);

    public boolean updateUserName(User user);

    public boolean updateUserPwd(User user);

    public boolean updateLocation(User user);

    public boolean updateUserHead(String userId, MultipartFile multipartFile, String rootPath);

    /**
     * 更新全部信息时一定要检查用户ID是否属实
     * @param user
     * @return
     */
    public boolean update(User user);

    public boolean deleteUser(String userId, String rootPath);

}
