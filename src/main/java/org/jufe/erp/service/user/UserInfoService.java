package org.jufe.erp.service.user;

import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.repository.BaseInterface;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface UserInfoService {

    public UserInfo findByUserId(String userId);

    public boolean update(UserInfo userInfo);

    /**
     * 清除详细信息
     * 若出错，则返回的list为null
     * @param userId
     * @return
     */
    public List<UserInfo> deleteByUserId(String userId);

}
