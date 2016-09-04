package org.jufe.erp.utils.enums;

/**
 * Created by Raomengnan on 2016/9/4.
 */
public enum ResourceEnum {
    NEWSIMAGE("resource/news"),
    PHOTO("resource/photos"),
    HEAD("resource/head"),
    TIMESHAFT("resource/timeshaft"),
    VIDEO("resource/homepage/videos"),
    IMAGE("resource/homepage/images");

    private String path;
    ResourceEnum(String path){
        this.path = path;
    }

    public String p(){
        return this.path;
    }
}
