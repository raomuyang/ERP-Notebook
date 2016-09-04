package org.jufe.erp.service.user.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.repository.user.UserInfoRepository;
import org.jufe.erp.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoRepository repository;

    private Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

    @Override
    public UserInfo findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public boolean update(UserInfo userInfo) {
        return repository.update(userInfo);
    }

    @Override
    public List<UserInfo> deleteByUserId(String userId) {
        return repository.deleteByUserId(userId);
    }
}
