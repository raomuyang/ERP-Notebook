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
}
