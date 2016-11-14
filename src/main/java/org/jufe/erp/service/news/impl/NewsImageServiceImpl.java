package org.jufe.erp.service.news.impl;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.QOSComponent;
import org.jufe.erp.repository.news.NewsImageRepository;
import org.jufe.erp.repository.news.NewsRepository;
import org.jufe.erp.service.news.NewsImageService;
import org.jufe.erp.service.news.NewsService;
import org.jufe.erp.utils.DateTools;
import org.jufe.erp.utils.MultipartFileSave;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class NewsImageServiceImpl implements NewsImageService {
    @Autowired
    private NewsImageRepository newsImageRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private QOSComponent qosComponent;

    private Logger logger = Logger.getLogger(NewsImageServiceImpl.class);
    @Override
    public Page<NewsImage> getImagePage(int pno, int pSize) {
        return newsImageRepository.findPage(pno, pSize);
    }

    @Override
    public boolean updateIntro(NewsImage newsImage) {
        return newsImageRepository.updateIntro(newsImage);
    }

    @Override
    public boolean deleteByUrl(String url) {
        List<NewsImage> newsImages = newsImageRepository.deleteByUrl(url);
        if(newsImages.size() > 0)
            try {
                qosComponent.getQos().deleteFile(url);
                return true;
            }catch (Exception e){
                logger.error(String.format("Delete by url[%s]:", url) + e);
                return true;
            }
        return false;
    }

    @Override
    public boolean deleteByNewsId(String newsId) {
        List<NewsImage> newsImages = newsImageRepository.deleteByNewsId(newsId);
        if(newsImages.size() > 0){
            for (NewsImage newsImage: newsImages){
                String key =  newsImage.getUrl();
                try {
                    qosComponent.getQos().deleteFile(key);
                }catch (Exception e){
                    logger.error(String.format("Delete by newsId[%s],", newsId) + e);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addImage(NewsImage newsImage) {
        return newsImageRepository.save(newsImage);
    }

    @Override
    public List<NewsImage> getImageByNewsId(String newsId) {
        return newsImageRepository.findByNewsId(newsId);
    }

    @Override
    public boolean uploadImage(NewsImage newsImage, MultipartFile multipartFile) {

        if(newsRepository.findById(newsImage.getNewsId()) != null){
            FileOutputStream fo = null;
            try {
                //新闻图片的上传日期就以传输到服务器上的时间为准
                newsImage.setDate(new Date(System.currentTimeMillis()));

                String id = new ObjectId().toHexString();
                String fileId = DateTools.dateFormat(newsImage.getDate(), "yyyyMMdd") + "/" + id;
                String url = MultipartFileSave.save2Qiniu(qosComponent.getQos(), multipartFile, ResourceEnum.NEWSIMAGE, fileId);

                if(url == null)
                    throw new Exception("Save to Qiniu failed");
                newsImage.setId(id);
                newsImage.setUrl(url);
                return addImage(newsImage);
            }catch (Exception e){
                logger.error(e);
                return false;
            }finally {
                if(fo != null)
                    try {
                        fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

        return false;
    }

    @Override
    public NewsImage getByUrl(String url) {
        List<NewsImage> newsImages = newsImageRepository.find(new Query(new Criteria("url").is(url)));
        if(newsImages == null || newsImages.size() == 0)
            return null;
        return newsImages.get(0);
    }
}
