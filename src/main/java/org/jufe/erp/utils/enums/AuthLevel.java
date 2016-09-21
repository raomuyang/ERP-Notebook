package org.jufe.erp.utils.enums;

/**
 * Created by raomengnan on 16-9-21.
 */
public enum AuthLevel {

    USER(1, "Normal registered user"),
    KEEPER(2, "Website maintenance person"),
    CONTROLLER(3, "People who audit informations"),
    ADMIN(9, "Supreme authority");

    int level;
    String desc;
    AuthLevel(int level, String describe){
        this.level = level;
        this.desc = describe;
    }

    public int level(){
        return this.level;
    }

    public String describe(){
        return this.desc;
    }
}
