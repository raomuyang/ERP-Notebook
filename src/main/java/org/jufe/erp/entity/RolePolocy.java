package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by raomengnan on 16-8-31.
 * 角色权限
 */
@Document(collection = "rolePol")
public class RolePolocy {
    private String id;
    private String roleId;
    private String policyId;
    private Date termD;//有效期

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

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public Date getTermD() {
        return termD;
    }

    public void setTermD(Date termD) {
        this.termD = termD;
    }

    @Override
    public String toString() {
        return "RolePolocy{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", policyId='" + policyId + '\'' +
                ", termD=" + termD +
                '}';
    }
}
