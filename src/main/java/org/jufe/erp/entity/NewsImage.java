package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Raomengnan on 2016/8/28.
 * 新闻动态等内容中的图片
 */
@Document(collection = "newsImage")
public class NewsImage implements Serializable{
    private String id;
    private String newsId;
    private String url;
    private Date date;
    private String intro; //图片描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "NewsImage{" +
                "id='" + id + '\'' +
                ", newsId='" + newsId + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
