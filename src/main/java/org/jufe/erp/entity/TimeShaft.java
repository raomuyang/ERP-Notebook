package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Raomengnan on 2016/8/28.
 * 时间轴
 * 根据时间和图片记录轨迹
 */
@Document(collection = "tShaft")
public class TimeShaft implements Serializable{
    private String id;
    private String url; //图片的url
    private Date date;
    private String intro; //简单的描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
