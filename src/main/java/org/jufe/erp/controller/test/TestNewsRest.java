//package org.jufe.erp.controller.test;
//
//import org.jufe.erp.entity.NewsImage;
//import org.jufe.erp.service.news.NewsImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by raomengnan on 16-9-12.
// */
//@RestController
//public class TestNewsRest {
//    @Autowired
//    private NewsImageService newsImageService;
//
//    @RequestMapping(value = "/testUploadNewsImage", method = RequestMethod.POST)
//    public String upload(NewsImage newsImage, MultipartFile multipartFile, HttpServletRequest request){
//        System.out.println(multipartFile);
//        System.out.println(newsImage);
//        newsImageService.uploadImage(newsImage, multipartFile);
//        return newsImage.getUrl();
//    }
//
//    @RequestMapping(value = "/testU", method = RequestMethod.POST)
//    public String upload(MultipartFile multipartFile, HttpServletRequest request){
//        System.out.println(multipartFile.getOriginalFilename());
//        return multipartFile.getOriginalFilename();
//    }
//}
