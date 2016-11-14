package org.jufe.erp.controller.rest.photo;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.TimeShaft;
import org.jufe.erp.repository.Page;
import org.jufe.erp.service.photo.TimeShaftService;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.AuthLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Raomengnan on 2016/9/20.
 */
@RestController
@RequestMapping("/rest/photo/timeshaft")
public class TimeShaftRestController {

    @Autowired
    private TimeShaftService timeShaftService;
    private Logger logger = Logger.getLogger(TimeShaftRestController.class);

    @RequestMapping("/info/{id}")
    public TimeShaft getInfo(@PathVariable("id") String id){
        logger.debug("Get time shaft node by id:" + id);
        return timeShaftService.findById(id);
    }
    @RequestMapping("/page/psize/{psize}/pno/{pno}")
    public Page<TimeShaft> getPage(@PathVariable("pno") int pno, @PathVariable("psize") int psize){
        logger.debug(String.format("get-page/%s/%s", psize, pno));
        return timeShaftService.findPage(pno, psize);
    }

    @AuthRequest(level = AuthLevel.KEEPER)
    @RequestMapping(value = "/update-info", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody TimeShaft shaftNode){
        logger.debug("update-intro:" + shaftNode);
        ModelMap map = new ModelMap();
        boolean res = false;
        String msg = null;
        if(shaftNode == null || shaftNode.getId() == null || shaftNode.getIntro() == null)
            msg = "参数错误，请检查";
        else {
            res = timeShaftService.updateInfo(shaftNode);
            if(!res)
                msg = "更新失败，请检查后重试";

        }

        if(!res)
            map.put("msg", msg);
        map.put("result", res);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest(level = AuthLevel.KEEPER)
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(@RequestBody String id, HttpServletRequest request){
        logger.debug("delete:" + id);
        ModelMap map = new ModelMap();
        boolean result = false;
        String msg = "";

        if(id == null || id.isEmpty())
            msg = "参数错误";
        else {
            result = timeShaftService.deleteNodeById(id);
            if(!result)
                msg = "删除失败，请检查后重试";
        }

        if(!result)
            map.put("msg", msg);
        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    /**
     * 上传的图片允许先不包含信息，待到上传结束后修改
     * @param shaftNode
     * @param imageFile
     * @param request
     * @return
     */
    @AuthRequest(level = AuthLevel.KEEPER)
    @RequestMapping(value = "/add-image", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> addImage(TimeShaft shaftNode, MultipartFile imageFile, HttpServletRequest request){
        logger.debug("/add-image:" + shaftNode + "," + imageFile);

        ModelMap map = new ModelMap();
        boolean result = false;

        result = timeShaftService.addImage(shaftNode, imageFile);

        if(!result)
            map.put("msg", "上传失败，请重试");
        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }
}
