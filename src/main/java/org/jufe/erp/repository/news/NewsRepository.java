package org.jufe.erp.repository.news;

import org.jufe.erp.entity.News;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
@Repository
public class NewsRepository extends BaseRepository<News>{

    public List<News> findByAuthor(String author){
        return super.find(new Query(new Criteria("author").is(author)));
    }

    public List<News> findByTitle(String title){
        Criteria criteria = MongoUtil.fuzzyCriteria("title", title);
        return super.find(new Query(criteria));
    }

    public List<News> findByKeyword(String keyword){
        Criteria c1 = MongoUtil.fuzzyCriteria("title", keyword);
        Criteria c2 = MongoUtil.fuzzyCriteria("context", keyword);
        Criteria criteria = new Criteria().orOperator(c1, c2);
        return super.find(new Query(criteria));
    }

}
