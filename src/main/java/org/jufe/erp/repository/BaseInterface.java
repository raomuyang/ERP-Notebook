package org.jufe.erp.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/31.
 */
public interface BaseInterface<T> {
    MongoOperations getMongoTemplate();

    List<T> findAll();

    List<T> findAll(String key, Sort.Direction direction);

    List<T> find(Query query);

    T findOne(Query query);

    T findById(String id);

    T findById(String id, String collection);

    boolean insert(T obj);

    /**
     * 调用save方法，当数据库中不存在，则创建，存在，则更新
     *
     * @param obj 要更新的对象
     */
    boolean save(T obj);

    boolean save(Query query, Update update);

    boolean update(Query query, Update update);

    boolean update(String id, String key, Object value);

    List<T> delete(Query query);

    boolean deleteById(String id);

    /**
     * 分页查询 无查询条件
     *
     * @param pno
     * @param pageSize
     * @return
     */
    Page<T> findPage(int pno, int pageSize);

    /**
     * 分页查询 有查询条件
     *
     * @param query
     * @param pno
     * @param pageSize
     * @return
     */
    Page<T> findPage(Query query, int pno, int pageSize);

    long count(Query query);

    long count();

    /**
     * @param query
     * @return 1：存在 0：不存在 -1：出错
     */
    int isExists(Query query);

}
