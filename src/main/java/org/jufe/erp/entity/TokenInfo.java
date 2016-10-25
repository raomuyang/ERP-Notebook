package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by raomengnan on 16-9-21.
 * Tocken的相关内容
 */
@Document(collection = "tockens")
public class TokenInfo {
    private String id;
    private String userId;
    private String token;
    private long validDays = 30 * 60 * 1000; //失效时间，默认为30分钟
    private Date lastTime;

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

    public long getValidDays() {
        return validDays;
    }

    public void setValidDays(long validDays) {
        this.validDays = validDays;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", validDays=" + validDays +
                ", lastTime=" + lastTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenInfo that = (TokenInfo) o;

        if (validDays != that.validDays) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (int) (validDays ^ (validDays >>> 32));
        return result;
    }
}
