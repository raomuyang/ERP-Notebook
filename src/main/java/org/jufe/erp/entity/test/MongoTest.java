package org.jufe.erp.entity.test;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/8/27.
 */
@Document(collection = "test")
public class MongoTest {
    private String id;
    private String name;
    private Date registDate;
    private double testDouble;
    private List<String> testList = new ArrayList<String>();

    public MongoTest() {}

    public MongoTest(String id, String name, Date registDate, double testDouble) {
        this.id = id;
        this.name = name;
        this.registDate = registDate;
        this.testDouble = testDouble;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(double testDouble) {
        this.testDouble = testDouble;
    }

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }
}
