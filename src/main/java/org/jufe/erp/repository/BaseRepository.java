package org.jufe.erp.repository;

import org.springframework.beans.factory.annotation.Autowired;
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

    public BaseRepository(){
        Type type = getClass().getGenericSuperclass();
        Type[] params = ( (ParameterizedType)type ).getActualTypeArguments();
        this.clazz = (Class<T>) params[0];
    }

    public List<T> find(Query query){
        return mongoTemplate.find(query, clazz);
    }

    public T findOne(Query query){
        return mongoTemplate.findOne(query, clazz);
    }

    public T findById(String id){
        return mongoTemplate.findById(id, clazz);
    }

    public T findById(String id, String collection){
        return mongoTemplate.findById(id, clazz, collection);
    }

    public T insert(T obj){
        mongoTemplate.insert(obj);
        return obj;
    }

    /**
     * 这个update调用save方法，当数据库中不存在，则创建，存在，则更新
     * @param obj 要更新的对象
     */
    public void update(T obj){
        mongoTemplate.save(obj);
    }

    public void update(Query query, Update update){
        mongoTemplate.findAndModify(query, update, clazz);
    }

    public List<T> delete(Query query){
        return mongoTemplate.findAllAndRemove(query, clazz);
    }

    public void deleteById(String id){
        mongoTemplate.remove(new Query(new Criteria("_id").is(id)), clazz);
    }

    public long count(Query query){
        return mongoTemplate.count(query, clazz);
    }

    public long count(){
        return mongoTemplate.count( new Query(), clazz);
    }

}
