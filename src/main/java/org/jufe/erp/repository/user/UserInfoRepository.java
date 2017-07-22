package org.jufe.erp.repository.user;

import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.repository.BaseInterface;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface UserInfoRepository extends BaseInterface<UserInfo> {

    UserInfo findByUserId(String userId);

    boolean update(UserInfo userInfo);

    /**
     * 清除详细信息
     * 若出错，则返回的list为null
     *
     * @param userId
     * @return
     */
    List<UserInfo> deleteByUserId(String userId);

}
