package org.jufe.erp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raomengnan on 2016/8/28.
 * Main Show
 * 展示在首页上的主要信息
 */
@Document(collection = "info")
public class MShow implements Serializable{
    private String id = "erp.mainshow";
    private List<String> iurls = new ArrayList<String>(); // 首页展示的图片链接
    private String vurl; //首页视频链接
    private String words; // 主页寄语
    private List<String> iHistory = new ArrayList<String>(); //历史图片
    private List<String> vHistory = new ArrayList<String>(); //历史视频

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIurls() {
        return iurls;
    }

    public void setIurls(List<String> iurls) {
        this.iurls = iurls;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public List<String> getiHistory() {
        return iHistory;
    }

    public void setiHistory(List<String> iHistory) {
        this.iHistory = iHistory;
    }

    public List<String> getvHistory() {
        return vHistory;
    }

    public void setvHistory(List<String> vHistory) {
        this.vHistory = vHistory;
    }
}
