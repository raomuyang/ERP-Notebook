package org.jufe.erp.repository.news.impl;

import org.jufe.erp.entity.News;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.news.NewsRepository;
import org.jufe.erp.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
@Repository
public class NewsRepositoryImpl extends BaseRepository<News> implements NewsRepository{

    private Criteria crFin = new Criteria("finish").is(true);

    public List<News> findByAuthorId(String authorId){
        return super.find(new Query(new Criteria("authorId").is(authorId)));
    }

    public List<News> findByAuthor(String author){
        Criteria criteria = MongoUtil.fuzzyCriteria("author", author);
        return super.find(new Query(new Criteria().andOperator(criteria, crFin)));
    }

    public List<News> findByTitle(String title){
        Criteria criteria = MongoUtil.fuzzyCriteria("title", title);
        return super.find(new Query(new Criteria().andOperator(criteria, crFin)));
    }

    public List<News> findByKeyword(String keyword){
        Criteria c1 = MongoUtil.fuzzyCriteria("title", keyword);
        Criteria c2 = MongoUtil.fuzzyCriteria("context", keyword);
        Criteria criteria = new Criteria().orOperator(c1, c2);
        return super.find(new Query(new Criteria().andOperator(criteria, crFin)));
    }

    public Page<News> findPage(int pno, int pSize){
        return super.findPage(MongoUtil.soryBy(new Query(crFin), MongoUtil.DESC, "date"), pno, pSize);
    }

    public List<News> findAll(){
        Query query = MongoUtil.soryBy(new Query(crFin),MongoUtil.DESC, "date");
        return super.find(query);
    }

    @Override
    public List<News> findUserNoFinished(String authorId) {
        return super.find(new Query(new Criteria("authorId").is(authorId)
                .and("finish").is(false)));
    }

    @Override
    public Page<News> findUserNoFinishedPage(String authorId, int pno, int psize) {
        return findPage(new Query(new Criteria("authorId").is(authorId)
                .and("finish").is(false)), pno, psize);
    }

    public boolean update(News news){
        return super.update(new Query(new Criteria("id").is(news.getId())),
                new Update().set("title", news.getTitle())
                        .set("context", news.getContext())
                        .set("finish", news.getFinish()));
    }


}
