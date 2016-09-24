package org.jufe.erp.utils.enums;

/**
 * Created by Raomengnan on 2016/9/22.
 */
public enum StandardStr {
    TOKEN("token"),
    USER("user");

    String str;
    StandardStr(String str){
        this.str = str;
    }

    public String s(){
        return this.str;
    }
}
