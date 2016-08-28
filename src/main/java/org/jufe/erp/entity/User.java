package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Raomengnan on 2016/8/28.
 * 用户基本信息
 */
@Document(collection = "users")
public class User implements Serializable{
    private String id;
    private String userName;
    private String pwd;
    private String userPhotoUrl; //头像url地址
    private int userGrade; //年级
    private String nowInWhere;
    private Date registerTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }


    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public String getNowInWhere() {
        return nowInWhere;
    }

    public void setNowInWhere(String nowInWhere) {
        this.nowInWhere = nowInWhere;
    }
}
