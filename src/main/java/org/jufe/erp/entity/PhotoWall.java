package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by Raomengnan on 2016/8/28.
 */
@Document(collection = "photoWall")
public class PhotoWall implements Serializable{
    private String id;
    private String userId; //nullable
    private String url; //照片url；
    private int grade; //年级
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "PhotoWall{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", grade=" + grade +
                ", userName='" + userName + '\'' +
                '}';
    }
}
