package org.jufe.erp.controller.rest.about;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.ERPInfo;
import org.jufe.erp.service.about.ERPInfoService;
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


/**
 * Created by raomengnan on 16-9-12.
 */
@RestController
@RequestMapping("/rest/about/info")
public class ErpInfoRestController {
    @Autowired
    private ERPInfoService erpInfoService;

    private Logger logger = Logger.getLogger(ErpInfoRestController.class);

    @RequestMapping("/base-info")
    public ERPInfo getBaseInfo(){
        logger.debug("/base-info");
        return erpInfoService.getInfo();
    }

    @RequestMapping("/tel")
    public String getTel(){
        logger.debug("/tel");
        return erpInfoService.getTel();
    }

    @RequestMapping("/intro")
    public String getIntro(){
        logger.debug("/intro");
        return erpInfoService.getIntro();
    }

    @RequestMapping("/joinus")
    public String getJoinUs(){
        logger.debug("/joinus");
        return erpInfoService.getJoinUs();
    }

    @RequestMapping("/org-struct")
    public String getStruct(){
        logger.debug("/org-struct");
        return erpInfoService.getOrgStruct();
    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> update(@RequestBody ERPInfo erpInfo){
        logger.debug("/update:" + erpInfo);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(erpInfo != null){
            result = erpInfoService.update(erpInfo);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-tel", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateTel(@RequestBody String tel){
        logger.debug("/update-tel: " + tel);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(tel != null){
            result = erpInfoService.updateTel(tel);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
                map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-intro", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateIntro(@RequestBody String intro){
        logger.debug("/update-intro: " + intro);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(intro != null){
            result = erpInfoService.updateIntro(intro);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-join", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateJoin(@RequestBody String join){
        logger.debug("/update-join: " + join);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(join != null){
            result = erpInfoService.updateJoinUs(join);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @AuthRequest(level = AuthLevel.CONTROLLER)
    @RequestMapping(value = "/update-org-staruct", method = RequestMethod.PUT)
    public ResponseEntity<ModelMap> updateOrg(@RequestBody String org){
        logger.debug("/update-org-staruct: " + org);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(org != null){
            result = erpInfoService.updateOrg(org);
            if(!result)
                map.put("msg", "更新失败，请重试");
        }
        else
            map.put("msg", "参数错误");
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }


}
