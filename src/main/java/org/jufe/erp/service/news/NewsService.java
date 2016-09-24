package org.jufe.erp.service.news;

import org.jufe.erp.entity.News;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface NewsService  {

    public News findById(String id);

    public List<News> findByAuthor(String author);

    public List<News> findByAuthorId(String authorId);

    public List<News> findByTitle(String title);

    public List<News> findByKeyword(String keyword);

    public Page<News> findPage(int pno, int pSize);

    public List<News> findAll();

    public List<News> findAuthorNoFinished(String authorId);

    public Page<News> findAuthorNoFinishedPage(String authorId, int pno, int psize);

    /**
     * 只更新标题\内容\状态
     * @param news
     * @return
     */
    public boolean update(News news);

    public boolean addNews(News news);

    public boolean delete(String newsId);

    public boolean updateStatus(News news);
}
