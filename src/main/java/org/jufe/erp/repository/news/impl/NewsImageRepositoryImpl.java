package org.jufe.erp.repository.news.impl;

import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.news.NewsImageRepository;
import org.jufe.erp.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by raomengnan on 16-8-31.
 */
@Repository
public class NewsImageRepositoryImpl extends BaseRepository<NewsImage> implements NewsImageRepository{

    public Page<NewsImage> findPage(int pno, int pSize){
        return super.findPage(MongoUtil.soryBy(new Query(), MongoUtil.DESC, "date"), pno, pSize);
    }

    public boolean update(NewsImage newsImage){
        return super.update(new Query(new Criteria("id").is(newsImage.getId())),
                new Update().set("intro", newsImage.getIntro()));
    }
}
