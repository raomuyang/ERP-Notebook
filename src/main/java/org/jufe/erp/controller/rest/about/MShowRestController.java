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

/**
 * Created by raomengnan on 16-9-12.
 */
@RestController
@RequestMapping("/rest/about/mshow")
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
    @RequestMapping(value = "/update-words", method = RequestMethod.POST)
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
    @RequestMapping(value = "/update-iurls", method = RequestMethod.POST)
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
    @RequestMapping(value = "/update-video", method = RequestMethod.POST)
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

    @RequestMapping(value = "/delete-image", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> deleteImage(@RequestBody String args, HttpServletRequest request){
        logger.debug("/delete-image: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null){
            result = mShowService.deleteImages(JsonUtils.jsonToList(args),
                    request.getSession().getServletContext().getRealPath("/"));
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/delete-video", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> deleteVideo(@RequestBody String args, HttpServletRequest request){
        logger.debug("/delete-video: " + args);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(args != null){
            result = mShowService.deleteVideos(JsonUtils.jsonToList(args),
                    request.getSession().getServletContext().getRealPath("/"));
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/upload-image", method = RequestMethod.PUT)
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
            else
                map.put("msg", "更新失败，请检查后重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/upload-video", method = RequestMethod.PUT)
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
            else
                map.put("msg", "更新失败，请检查后重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
