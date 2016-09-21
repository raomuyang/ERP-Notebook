package org.jufe.erp.utils.enums;

/**
 * Created by Raomengnan on 2016/8/28.
 */
public enum RequestEnum {
    READ("READ", "GET"),
    WRITE("WRITE", "PUT"),
    UPDATE("UPDATE", "POST"),
    DELETE("DELETE", "DELETE");

    private String auth;
    private String method;
    RequestEnum(String auth, String method){
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
