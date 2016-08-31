package org.jufe.erp.repository.news;

import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.Page;
import org.jufe.erp.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface NewsImageRepository extends BaseInterface<NewsImage> {

    public Page<NewsImage> findPage(int pno, int pSize);

    public boolean update(NewsImage newsImage);
}
