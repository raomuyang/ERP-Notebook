package org.jufe.erp.service.news.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.News;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.news.NewsRepository;
import org.jufe.erp.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;

    private Logger logger = Logger.getLogger(NewsServiceImpl.class);

    @Override
    public News findById(String id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> findByAuthor(String author) {
        return newsRepository.findByAuthor(author);
    }

    @Override
    public List<News> findByTitle(String title) {
        return newsRepository.findByTitle(title);
    }

    @Override
    public List<News> findByKeyword(String keyword) {
        return newsRepository.findByKeyword(keyword);
    }

    @Override
    public Page<News> findPage(int pno, int pSize) {
        return newsRepository.findPage(pno, pSize);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    /**
     * 只能更新标题和内容
     * @param news
     * @return
     */
    @Override
    public boolean update(News news) {
        return newsRepository.update(news);
    }

    @Override
    public boolean addNews(News news) {
        return newsRepository.insert(news);
    }
}
