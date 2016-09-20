package org.jufe.erp.controller.rest.photo;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.PhotoWall;
import org.jufe.erp.repository.Page;
import org.jufe.erp.service.photo.PhotoWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/20.
 */
@RestController
@RequestMapping("/rest/photo/photo-wall")
public class PhotoWallRestController {

    @Autowired
    private PhotoWallService photoWallService;

    private Logger logger = Logger.getLogger(PhotoWallRestController.class);

    @RequestMapping("get-by-grade/{grade}")
    public List<PhotoWall> getByGrade(@PathVariable("grade") int grade){
        logger.debug("get by grade:" + grade);
        return photoWallService.getPhotosByGrade(grade);
    }

    @RequestMapping("search/{username}")
    public List<PhotoWall> getByNameLike(@PathVariable("username") String userName){
        logger.debug("Search by userName:" + userName);
        return photoWallService.getPhotosByUserNameLike(userName);
    }

    @RequestMapping("get-all")
    public List<PhotoWall> getAll(){
        logger.debug("getAll");
        return photoWallService.getAll();
    }

    @RequestMapping("get-page/{pno}/{psize}")
    public Page<PhotoWall> getPage(@PathVariable("pno") int pno, @PathVariable("psize") int psize){
        logger.debug(String.format("get-page/%s/%s", pno, psize));
        return photoWallService.getPage(pno, psize);
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(@RequestBody String id, HttpServletRequest request){
        logger.debug("delete:" + id);
        boolean result = false;
        ModelMap map = new ModelMap();
        if(id == null || id.isEmpty())
            map.put("msg", "参数错误，请检查后重试");
        else {
            result = photoWallService.deleteByPhotoId(id,
                    request.getSession().getServletContext().getRealPath("/"));
            if(!result)
                map.put("msg", "删除出错，请检查后重试");
        }

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    /**
     *
     * @param photoInfo
     * {"id":"", "userName":"", "grade":""}
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody PhotoWall photoInfo){
        logger.debug("update-username:" + photoInfo);
        boolean result = false;
        ModelMap map = new ModelMap();
        if(photoInfo == null)
            map.put("msg", "参数错误，请检查后重试");
        else {
            result = photoWallService.updateUserInfo(photoInfo);
            if(!result)
                map.put("msg", "更新出错，请检查后重试");
        }

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "update-photo", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updatePhoto(String id, MultipartFile imageFile, HttpServletRequest request){
        logger.debug("update-photo:" + id + "," + imageFile);
        boolean result = false;
        ModelMap map = new ModelMap();

        String rootPath = request.getSession().getServletContext().getRealPath("/");
        result = photoWallService.updateUserPhoto(id, imageFile, rootPath);
        if(!result)
            map.put("msg", "上传失败，请检查后重试");

        map.put("result", result);

        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "upload-photo", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> uploadPhoto(PhotoWall photoInfo, MultipartFile imageFile, HttpServletRequest request){
        logger.debug("upload-photo：" + photoInfo + "," + imageFile );

        boolean result = false;
        ModelMap map = new ModelMap();
        result = photoWallService.addUserPhoto(photoInfo, imageFile,
                request.getSession().getServletContext().getRealPath("/"));

        if(!result)
            map.put("msg", "上传失败，请重试");
        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }


}
