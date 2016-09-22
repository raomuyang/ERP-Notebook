package org.jufe.erp.service.auth;

import org.jufe.erp.entity.TokenInfo;
import org.jufe.erp.entity.User;

/**
 * Created by raomengnan on 16-9-21.
 * 将Tocken存储到mongoDB中
 */
public interface TocknService {

    public TokenInfo get(String token);
    public User getUser(String token);
    public TokenInfo create(String userId);
    public boolean delete(String token);
    public boolean update(String token);
}
