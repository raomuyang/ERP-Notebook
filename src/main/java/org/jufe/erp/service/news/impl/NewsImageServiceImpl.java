package org.jufe.erp.service.news.impl;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.news.NewsImageRepository;
import org.jufe.erp.service.news.NewsImageService;
import org.jufe.erp.utils.DateTool;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class NewsImageServiceImpl implements NewsImageService {
    @Autowired
    private NewsImageRepository newsImageRepository;

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
    public boolean deleteByUrl(String realPath, String url) {
        String path = realPath + "/" + url;
        List<NewsImage> newsImages = newsImageRepository.deleteByUrl(url);
        if(newsImages.size() > 0)
            try {
                File file = new File(path);
                if(file.exists())
                    file.delete();
                return true;
            }catch (Exception e){
                logger.error(String.format("Delete by url[%s]:", path) + e.getMessage());
                return true;
            }
        return false;
    }

    @Override
    public boolean deleteByNewsId(String realPath, String newsId) {
        List<NewsImage> newsImages = newsImageRepository.deleteByNewsId(newsId);
        if(newsImages.size() > 0){
            for (NewsImage newsImage: newsImages){
                String path = realPath + "/" + newsImage.getUrl();
                try {
                    File file = new File(path);
                    file.delete();
                }catch (Exception e){
                    logger.error(String.format("Delete by newsId[%s],", newsId) + e.getMessage());
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
    public boolean uploadImage(NewsImage newsImage, MultipartFile multipartFile, String realPath) {
        FileOutputStream fo = null;
        try {
            String rootPath = realPath;
            String subPath =  ResourceEnum.NEWSIMAGE.p() + "/" + DateTool.dateFormat(newsImage.getDate(), "yyyyMMdd") + "/" + newsImage.getNewsId();
            String fileId = new ObjectId().toString();
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            String path = rootPath + "/" + subPath + "/";
            File filePath = new File(path);
            if(!filePath.exists())
                filePath.mkdirs();
            File file = new File(filePath, fileId  + suffix);
            fo = new FileOutputStream(file);
            fo.write(multipartFile.getBytes());

            newsImage.setId(fileId);
            newsImage.setUrl("/" + subPath + "/" + fileId + suffix);
            return addImage(newsImage);
        }catch (Exception e){
            logger.error(e.getMessage());
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
}
