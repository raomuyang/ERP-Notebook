package org.jufe.erp.service.auth;

import org.jufe.erp.entity.TockenInfo;
import org.jufe.erp.entity.User;

/**
 * Created by raomengnan on 16-9-21.
 * 将Tocken存储到mongoDB中
 */
public interface TockenService {

    public TockenInfo get(String id);
    public User getUser(String id);
    public boolean delete(String id);
}
