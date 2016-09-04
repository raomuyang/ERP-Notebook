package org.jufe.erp.service.news;

import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface NewsImageService{

    public Page<NewsImage> getImagePage(int pno, int pSize);

    public boolean updateIntro(NewsImage newsImage);

    public boolean deleteByUrl(String realPath,String url);

    public boolean deleteByNewsId(String realPath,String newsId);

    public boolean addImage(NewsImage newsImage);

    public List<NewsImage> getImageByNewsId(String newsId);

    public boolean uploadImage(NewsImage newsImage, MultipartFile multipartFile, String realPath);
}
