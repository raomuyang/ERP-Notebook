package org.jufe.erp.service.user.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.User;
import org.jufe.erp.repository.QOSComponent;
import org.jufe.erp.repository.user.UserRepository;
import org.jufe.erp.service.user.UserService;
import org.jufe.erp.utils.FileUtils;
import org.jufe.erp.utils.MD5;
import org.jufe.erp.utils.MultipartFileSave;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class UserServicesImpl implements UserService{

    @Autowired
    private UserRepository repository;
    @Autowired
    private QOSComponent qosComponent;

    private Logger logger = Logger.getLogger(UserServicesImpl.class);

    @Override
    public User findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<User> findByGrade(int grade) {
        return repository.findByGrade(grade);
    }

    @Override
    public List<User> findByLocation(String location) {
        return repository.findByLocation(location);
    }

    /**
     * 使用MD5验证密码
     * @param id
     * @param pwd
     * @return
     */
    @Override
    public User checkLogin(String id, String pwd) {
        String pwdMD5 = MD5.getMD5(pwd);
        return repository.findUserByIdAndPwd(id, pwdMD5);
    }

    @Override
    public boolean addUser(User user, MultipartFile multipartFile) {

        user.setPwd(MD5.getMD5(user.getPwd()));
        user.setRegisterTime(new Date(System.currentTimeMillis()));
        boolean res = repository.addUser(user);
        if(res)
            try {

                String fileId = user.getId();
                String url = MultipartFileSave.save2Qiniu(qosComponent.getQos(), multipartFile, ResourceEnum.HEAD, fileId);

                if(url != null)
                    user.setUserPhotoUrl(url);
                else
                    user.setUserPhotoUrl("/" + ResourceEnum.HEAD.p() + "/default/" + "default.jpg");
            }catch (Exception e){
                user.setUserPhotoUrl("/" + ResourceEnum.HEAD.p() + "/default/" + "default.jpg");
            }finally {
                repository.update(user);
            }

        return res;
    }

    @Override
    public boolean updateUserName(User user) {
        if(user == null || user.getUserName() == null || user.getId() ==null)
            return false;
        return repository.updateUserName(user);
    }

    @Override
    public boolean updateUserPwd(User user) {
        if(user == null || user.getPwd() == null || user.getId() ==null)
            return false;
        user.setPwd(MD5.getMD5(user.getPwd()));
        return repository.updateUserPwd(user);
    }

    @Override
    public boolean updateLocation(User user) {
        if(user == null || user.getNowInWhere() == null || user.getId() ==null)
            return false;
        return repository.updateLocation(user);
    }

    @Override
    public boolean updateUserHead(String userId, MultipartFile multipartFile) {
        try {
            String url = MultipartFileSave.save2Qiniu(
                    qosComponent.getQos(), multipartFile, ResourceEnum.HEAD, userId);

            User user = repository.findById(userId);
            user.setUserPhotoUrl(url);

            if(url != null)
                return repository.update(user);
        } catch (Exception e){
            logger.error("Update User's Head failed");
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        User u = findById(user.getId());
        if(user.getPwd() != null)
            u.setPwd(MD5.getMD5(user.getPwd()));

        if(user.getRegisterTime() != null)
            u.setNowInWhere(user.getNowInWhere());

        if(user.getUserName() != null)
            u.setUserName(user.getUserName());
        return repository.update(u);
    }

    @Override
    public boolean deleteUser(String userId) {

        User user = repository.findById(userId);
        boolean res = repository.deleteById(userId);
        if(res)
            try {
                if(!user.getUserPhotoUrl().contains("/default/"))
                    qosComponent.getQos().deleteFile(user.getUserPhotoUrl());
            }catch (Exception e){
                logger.error("delete user:" + e);
            }
        return res;
    }
}
