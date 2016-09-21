package org.jufe.erp.utils.mapdb.db;

/**
 * Created by raomengnan on 16-9-21.
 */
public class Test extends MapDBInfo{

    String key;
    int a;

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Test(String key, int a) {
        super(key);
        this.key = key;
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

}
