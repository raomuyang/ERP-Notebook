package org.jufe.erp.utils.enums;

/**
 * Created by Raomengnan on 2016/9/4.
 */
public enum ResourceEnum {
    NEWSIMAGE("/news"),
    PHOTO("/photos"),
    TIMESHAFT("/timeshaft"),
    VIDEO("/videos");

    private String path;
    ResourceEnum(String path){
        this.path = path;
    }

    public String p(){
        return this.path;
    }
}
