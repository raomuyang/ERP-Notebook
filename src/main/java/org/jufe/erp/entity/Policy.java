package org.jufe.erp.entity;

import org.jufe.erp.utils.enums.RequestEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raomengnan on 2016/8/28.
 * 对角色的授权策略
 */
@Document(collection = "policy")
public class Policy implements Serializable{
    private String id;
    private Map<RequestEnum, Boolean> auth ; // 权限

    public Policy(){
        auth = new HashMap<>();
        auth.put(RequestEnum.READ, false);
        auth.put(RequestEnum.WRITE, false);
        auth.put(RequestEnum.UPDATE, false);
        auth.put(RequestEnum.UPDATE, false);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<RequestEnum, Boolean> getAuth() {
        return auth;
    }

    public void setAuth(Map<RequestEnum, Boolean> auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id='" + id + '\'' +
                ", auth=" + auth +
                '}';
    }
}
