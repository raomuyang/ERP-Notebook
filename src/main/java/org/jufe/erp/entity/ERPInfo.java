package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Raomengnan on 2016/8/28.
 */
@Document(collection = "info")
public class ERPInfo implements Serializable{
    private String id = "org.jufe.erp";
    private String tel; //联系电话
    private String intro; //协会介绍
    private String joinUs; //加入我们
    private String org; //组织架构 html格式

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getJoinUs() {
        return joinUs;
    }

    public void setJoinUs(String joinUs) {
        this.joinUs = joinUs;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "ERPInfo{" +
                "id='" + id + '\'' +
                ", tel='" + tel + '\'' +
                ", intro='" + intro + '\'' +
                ", joinUs='" + joinUs + '\'' +
                ", org='" + org + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ERPInfo erpInfo = (ERPInfo) o;

        if (id != null ? !id.equals(erpInfo.id) : erpInfo.id != null) return false;
        if (tel != null ? !tel.equals(erpInfo.tel) : erpInfo.tel != null) return false;
        if (intro != null ? !intro.equals(erpInfo.intro) : erpInfo.intro != null) return false;
        if (joinUs != null ? !joinUs.equals(erpInfo.joinUs) : erpInfo.joinUs != null) return false;
        return org != null ? org.equals(erpInfo.org) : erpInfo.org == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (intro != null ? intro.hashCode() : 0);
        result = 31 * result + (joinUs != null ? joinUs.hashCode() : 0);
        result = 31 * result + (org != null ? org.hashCode() : 0);
        return result;
    }
}
