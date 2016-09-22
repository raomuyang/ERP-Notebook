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

    public static RequestEnum toCase(String name){
        name = name.toUpperCase();
        if(name.equals(READ.value()))
            return READ;
        if(name.equals(WRITE.value()))
            return WRITE;
        if(name.equals(UPDATE.value()))
            return UPDATE;
        if(name.equals(DELETE.value()))
            return DELETE;
        else
            return null;
    }
}
