package org.jufe.erp.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Raomengnan on 2016/8/28.
 */
public abstract class BaseRepository<T>{

    @Autowired
    private MongoOperations mongoTemplate;
    private Class<T> clazz;
    protected Logger logger;

    public BaseRepository(){
        Type type = getClass().getGenericSuperclass();
        Type[] params = ( (ParameterizedType)type ).getActualTypeArguments();
        this.clazz = (Class<T>) params[0];

        this.logger = Logger.getLogger(getClass());
    }

    public MongoOperations getMongoTemplate(){
        return this.mongoTemplate;
    }

    public List<T> findAll(){

        logger.debug("find All");
        try {
            return mongoTemplate.findAll(clazz);
        }catch (Exception e){
            logger.error(e.toString());
            return null;
        }

    }

    public List<T> findAll(String key, Sort.Direction direction){

        logger.debug("find All by " + key + ", " + direction.toString());
        try {
            return mongoTemplate.find(new Query().with(new Sort(direction, key)), clazz);
        }catch (Exception e){
            logger.error(e.toString());
            return null;
        }
    }

    public List<T> find(Query query){

        logger.debug("find: " + query.toString());
        try {
            return mongoTemplate.find(query, clazz);
        }catch (Exception e){
            logger.error(e.toString());
            return null;
        }
    }

    public T findOne(Query query){

        logger.debug("findOne: " + query.toString());
        try {
            return mongoTemplate.findOne(query, clazz);
        } catch (Exception e){
            logger.error(e.toString());
            return null;
        }
    }

    public T findById(String id){

        logger.debug("findById: " + id);
        try {
            return mongoTemplate.findById(id, clazz);
        } catch (Exception e){
            logger.error(e.toString());
            return null;
        }
    }

    public T findById(String id, String collection){

        logger.debug("findById:" + String.format("id[%s],collection[%s]",id,collection));
        try {
            return mongoTemplate.findById(id, clazz, collection);
        }catch (Exception e){
            logger.error(e.toString());
            return null;
        }
    }

    public boolean insert(T obj){
        logger.debug("insert:"+obj.toString());
        try {
            mongoTemplate.insert(obj);
            return true;
        }catch (Exception e){
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * 调用save方法，当数据库中不存在，则创建，存在，则更新
     * @param obj 要更新的对象
     */
    public boolean save(T obj){
        logger.info("save:" + obj.toString());
        try {
            mongoTemplate.save(obj);
            return true;
        }catch (Exception e){
            logger.error(e.toString());
            return false;
        }
    }

    public boolean save(Query query, Update update){
        logger.info("save:" + query.toString() + " ; " + update.toString());
        try {
            mongoTemplate.findAndModify(query, update, clazz);
            return true;
        }catch (Exception e){
            logger.error(e.toString());
            return false;
        }
    }

    public boolean update(Query query, Update update){
        logger.info("update:"+query.toString()+";--"+update.toString());
        try {
            mongoTemplate.updateMulti(query, update, clazz);
            return true;
        }catch (Exception e){
            logger.error(e.toString());
            return false;
        }

    }

    public List<T> delete(Query query){
        logger.info("delete:"+query.toString());
        try {
            return mongoTemplate.findAllAndRemove(query, clazz);
        }catch (Exception e){
            logger.error(e.toString());
            return null;
        }

    }

    public boolean deleteById(String id){
        logger.info("DELETE: By id " + id);
        try {
            mongoTemplate.remove(new Query(new Criteria("_id").is(id)), clazz);
            return true;
        }catch (Exception e){
            logger.error(e.toString());
            return false;
        }

    }

    public long count(Query query){
        logger.debug("COUNT: " + query.toString());
        try {
            return mongoTemplate.count(query, clazz);
        }catch (Exception e){
            logger.error(e.toString());
            return  -1;
        }

    }

    public long count(){
        logger.debug("COUNT ALL");
        try {
            return mongoTemplate.count( new Query(), clazz);
        }catch (Exception e){
            logger.error(e);
            return -1;
        }

    }

    /**
     *
     * @param query
     * @return 1：存在 0：不存在 -1：出错
     */
    public int isExists(Query query){
        logger.debug("is Exists: " + query.toString());
        try {
            boolean re = mongoTemplate.exists(query, clazz);
            if(re)
                return 1;
            return 0;
        }catch (Exception e){
            logger.error(e.toString());
            return -1;
        }

    }


}

class Page{

}
