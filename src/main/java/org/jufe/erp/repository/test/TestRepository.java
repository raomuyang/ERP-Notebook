package org.jufe.erp.repository.test;

import org.jufe.erp.entity.test.MongoTest;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Raomengnan on 2016/8/27.
 */
@Repository
public class TestRepository extends BaseRepository<MongoTest> {
    @Autowired
    private MongoOperations mongoTemplate;

    public void addTest(MongoTest test){
        mongoTemplate.insert(test);
    }

    public MongoTest getTestById(String id){
        return super.findById(id);
    }

    public void update(MongoTest test){
        mongoTemplate.save(test);
    }

    public void deleteById(String id){
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), MongoTest.class);
    }

    public long testCount(){
        return super.count();
    }
}
