package org.jufe.erp.repository.news;

import org.jufe.erp.entity.News;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface NewsRepository extends BaseInterface<News> {

    List<News> findByAuthor(String author);

    List<News> findByTitle(String title);

    List<News> findByKeyword(String keyword);

    Page<News> findPage(int pno, int pSize);

    List<News> findAll();

    List<News> findUserNoFinished(String userId);

    Page<News> findUserNoFinishedPage(String userId, int pno, int psize);

    boolean update(News news);

    List<News> findByAuthorId(String authorId);

}
