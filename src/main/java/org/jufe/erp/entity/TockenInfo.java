package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by raomengnan on 16-9-21.
 * Tocken的相关内容
 */
@Document(collection = "tockens")
public class TockenInfo {
    private String id;
    private String userId;

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

    @Override
    public String toString() {
        return "TockenInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TockenInfo that = (TockenInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
