package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raomengnan on 2016/8/28.
 * 对角色的授权策略
 */
@Document(collection = "policy")
public class Policy implements Serializable{
    private String id;
    private String roleId;
    private List<String> auth; // 权限

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getAuth() {
        return auth;
    }

    public void setAuth(List<String> auth) {
        this.auth = auth;
    }
}
