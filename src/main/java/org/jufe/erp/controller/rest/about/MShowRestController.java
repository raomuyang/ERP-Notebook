package org.jufe.erp.controller.rest.about;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.MShow;
import org.jufe.erp.service.about.MShowService;
import org.jufe.erp.utils.JsonUtils;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.AuthLevel;
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

/**
 * Created by raomengnan on 16-9-12.
 */
@RestController
@RequestMapping("/rest/about/mshow")
public class MShowRestController {
    @Autowired
    private MShowService mShowService;

    private Logger logger = Logger.getLogger(MShowRestController.class);

    @RequestMapping("/main")
    public MShow getMShow(){
        logger.debug("/main");
        return mShowService.getMShow();
    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> update(@RequestBody MShow mShow){
        logger.debug("/update: " + mShow);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(mShow != null)
            result = mShowService.update(mShow);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    /**
     * 更新首页寄语
     * @param words
     * @return
     */
    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-words", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateWords(@RequestBody String words){
        logger.debug("/update-words: " + words);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(words != null){
            result = mShowService.updateWords(words);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    /**
     * 更新首页图片的链接（上传图片后，会将图片链接返回）
     * @param args
     * @return
     */
    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-iurls", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateIUrls(@RequestBody String args){
        logger.debug("/update-iurls: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null){
            result = mShowService.updateIurls(JsonUtils.jsonToList(args));
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    /**
     * 更新首页视频链接
     * @param vurl
     * @return
     */
    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-video", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateVUrl(@RequestBody String vurl){
        logger.debug("/update-video: " + vurl);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(vurl != null){
            result = mShowService.updateVurl(vurl);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/delete-image", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> deleteImage(@RequestBody String args, HttpServletRequest request){
        logger.debug("/delete-image: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null){
            result = mShowService.deleteImages(JsonUtils.jsonToList(args));
            if(!result)
                map.put("msg", "删除失败，请刷新重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/delete-video", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> deleteVideo(@RequestBody String args, HttpServletRequest request){
        logger.debug("/delete-video: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null){
            result = mShowService.deleteVideos(JsonUtils.jsonToList(args));
            if(!result)
                map.put("msg", "删除失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/upload-image", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> uploadImage(MultipartFile image, HttpServletRequest request){
        logger.debug("/upload-image");
        ModelMap map = new ModelMap();
        boolean result = false;
        if(image != null){
            String url = mShowService.uploadImage(image);
            map.put("url", url);
            if(url != null && !url.equals(""))
                result = true;
            else
                map.put("msg", "上传失败，请检查后重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/upload-video", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> uploadVideo(MultipartFile video, HttpServletRequest request){
        logger.debug("/upload-video");
        ModelMap map = new ModelMap();
        boolean result = false;
        if(video != null){
            String url = mShowService.uploadVideo(video);
            map.put("url", url);
            if(url != null && !url.equals(""))
                result = true;
            else
                map.put("msg", "更新失败，请检查后重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
