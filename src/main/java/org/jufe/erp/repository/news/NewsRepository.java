package org.jufe.erp.repository.news;

import org.jufe.erp.entity.News;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface NewsRepository extends BaseInterface<News> {

    public List<News> findByAuthor(String author);

    public List<News> findByTitle(String title);

    public List<News> findByKeyword(String keyword);

    public Page<News> findPage(int pno, int pSize);

    public List<News> findAll();

    public boolean update(News news);


}
