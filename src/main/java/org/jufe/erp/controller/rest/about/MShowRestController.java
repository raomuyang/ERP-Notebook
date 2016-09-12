package org.jufe.erp.controller.rest.about;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.MShow;
import org.jufe.erp.service.about.MShowService;
import org.jufe.erp.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by raomengnan on 16-9-12.
 */
@RestController
@RequestMapping("/about/mshow")
public class MShowRestController {
    @Autowired
    private MShowService mShowService;

    private Logger logger = Logger.getLogger(MShowRestController.class);

    @RequestMapping("/get")
    public MShow getMShow(){
        logger.debug("/get");
        return mShowService.getMShow();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(MShow mShow){
        logger.debug("/update: " + mShow);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(mShow != null)
            mShowService.update(mShow);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    /**
     * 更新首页寄语
     * @param words
     * @return
     */
    @RequestMapping(value = "/update-words", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateWords(@RequestBody String words){
        logger.debug("/update-words: " + words);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(words != null)
            mShowService.updateWords(words);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    /**
     * 更新首页图片的链接（上传图片后，会将图片链接返回）
     * @param args
     * @return
     */
    @RequestMapping(value = "/update-iurls", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateIUrls(@RequestBody String args){
        logger.debug("/update-iurls: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null)
            mShowService.updateIurls(JsonUtils.jsonToList(args));
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    /**
     * 更新首页视频链接
     * @param vurl
     * @return
     */
    @RequestMapping(value = "/update-video", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateVUrls(@RequestBody String vurl){
        logger.debug("/update-video: " + vurl);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(vurl != null)
            mShowService.updateVurl(vurl);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/delete-image", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> deleteImage(@RequestBody String args, HttpServletRequest request){
        logger.debug("/delete-image: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null)
            mShowService.deleteImages(JsonUtils.jsonToList(args),
                    request.getSession().getServletContext().getRealPath("/"));
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/delete-video", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateVHistory(@RequestBody String args, HttpServletRequest request){
        logger.debug("/delete-video: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null)
            mShowService.deleteVideos(JsonUtils.jsonToList(args),
                    request.getSession().getServletContext().getRealPath("/"));
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/upload-image", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> uploadImage(MultipartFile originFile, HttpServletRequest request){
        logger.debug("/upload-iamge");
        ModelMap map = new ModelMap();
        boolean result = false;
        if(originFile != null){
            String url = mShowService.uploadImage(originFile,
                    request.getSession().getServletContext().getRealPath("/"));
            map.put("url", url);
            if(url != null && !url.equals(""))
                result = true;
        }
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/upload-video", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> uploadVideo(MultipartFile originFile, HttpServletRequest request){
        logger.debug("/upload-iamge");
        ModelMap map = new ModelMap();
        boolean result = false;
        if(originFile != null){
            String url = mShowService.uploadVideo(originFile,
                    request.getSession().getServletContext().getRealPath("/"));
            map.put("url", url);
            if(url != null && !url.equals(""))
                result = true;
        }
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
