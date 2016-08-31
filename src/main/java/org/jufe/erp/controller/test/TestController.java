package org.jufe.erp.controller.test;

import org.bson.types.ObjectId;
import org.jufe.erp.entity.test.MongoTest;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.test.TestRepository;
import org.jufe.erp.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by raomengnan on 16-8-25.
 */
@RestController
public class TestController {


    @Autowired
    private TestRepository testRepository;

    @RequestMapping("/test")
    public String test(){
        String str = "Test123";
        return str;

    }

    @RequestMapping("/testAdds")
    public String testAdd(String idp){
        for (int i = 0; i < 100; ++i){
            MongoTest test = new MongoTest();
            test.setId(idp + i);
            test.setName("test"+i);
            test.setTestDouble(Math.random());
            test.setRegistDate(new Date());
            testRepository.addTest(test);
        }

        return "finish";
    }

    @RequestMapping("/testFind")
    public MongoTest testFind(String id){
        MongoTest  test = testRepository.getTestById(id);
        return test;

    }

    @RequestMapping("/testUpdate")
    public boolean testUpdate(){
        MongoTest  test = testRepository.getTestById("testid10");
        test.setName("hhhhhhhhhhhhhhhhhhhhhhhhhh");
        return testRepository.update(test);

    }

    @RequestMapping("/testDelete")
    public boolean testDelete(String id){
        return testRepository.deleteById(id);
    }

    @RequestMapping("/testCount")
    public long testCount(){
        return testRepository.testCount();
    }

    @RequestMapping("/testFindByD")
    public List<MongoTest> testFindByDate(){
        Date d1 = DateTool.getDateBeforXDay(new Date(System.currentTimeMillis()),10);
        Date d2 = new Date(System.currentTimeMillis());
        return testRepository.findByDate(d1, d2);
    }

    @RequestMapping("/testMohu")
    public List<MongoTest> testMohu(String id){
        return testRepository.findById_Mohu(id);
    }

    @RequestMapping("/testFindAll")
    public List<MongoTest> testFindAllDESC(){
        return testRepository.testFindAllDESC();
    }

    @RequestMapping("/testGetPage")
    public Page<MongoTest> testGetPage(int pno){
        return testRepository.testGetPage(pno, 15);
    }

    @RequestMapping("/testGetPageByQuery")
    public Page<MongoTest> testGetPageByQuery(){
        return testRepository.testGetPageByQuery();
    }
    public static void main(String[] args) {
        System.out.println(new ObjectId("57c2b04b0ab75d2b98344ae3").getDate());
    }


}
