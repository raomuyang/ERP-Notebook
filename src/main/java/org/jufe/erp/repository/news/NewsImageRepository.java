package org.jufe.erp.repository.news;

import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;

import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface NewsImageRepository extends BaseInterface<NewsImage> {

    Page<NewsImage> findPage(int pno, int pSize);

    List<NewsImage> findByNewsId(String newsId);

    boolean updateIntro(NewsImage newsImage);

    List<NewsImage> deleteByNewsId(String newsId);

    List<NewsImage> deleteByUrl(String url);
}
