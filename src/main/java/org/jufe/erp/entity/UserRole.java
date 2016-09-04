package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by raomengnan on 16-8-31.
 */
@Document(collection = "userRole")
public class UserRole {
    private String id;
    private String userId;
    private String roleId;
    private Date termD; //角色绑定有效截止日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getTermD() {
        return termD;
    }

    public void setTermD(Date termD) {
        this.termD = termD;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", termD=" + termD +
                '}';
    }
}
