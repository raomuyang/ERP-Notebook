package org.jufe.erp.utils.enums;

/**
 * Created by Raomengnan on 2016/8/28.
 */
public enum AuthEnum {
    READ("read", "GET"),
    WRITE("write", "PUT"),
    UPDATE("update", "POST"),
    DELETE("delete", "DELETE");

    private String auth;
    private String method;
    AuthEnum(String auth, String method){
        this.auth = auth;
        this.method =method;
    }

    public String value(){
        return this.auth;
    }

    public String method(){
        return this.method;
    }

}
