package org.jufe.erp.repository.test;

import org.apache.log4j.Logger;
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

    private Logger logger = Logger.getLogger(TestRepository.class);

    public void addTest(MongoTest test){
        logger.debug("testAdd");
        mongoTemplate.insert(test);
    }

    public MongoTest getTestById(String id){
        logger.info("testGet");
        return super.findById(id);
    }

    public void update(MongoTest test){
        mongoTemplate.save(test);
    }

    public void deleteById(String id){
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), MongoTest.class);
    }

    public long testCount(){
        logger.debug("testCount");
        return super.count();
    }
}
