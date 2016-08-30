package org.jufe.erp.repository.test;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.test.MongoTest;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Raomengnan on 2016/8/27.
 */
@Repository
public class TestRepository extends BaseRepository<MongoTest> {

    private Logger logger = Logger.getLogger(TestRepository.class);

    public boolean addTest(MongoTest test){
        return super.insert(test);
    }

    public MongoTest getTestById(String id){
        return super.findById(id);
    }

    public boolean update(MongoTest test){
        if(test.getId() != null)
            return super.save(test);
        return false;
    }

    public boolean deleteById(String id){
        return super.deleteById(id);
    }

    public long testCount(){
        return super.count();
    }
}
