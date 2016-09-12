package org.jufe.erp.controller.rest.about;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.ERPInfo;
import org.jufe.erp.service.about.ERPInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by raomengnan on 16-9-12.
 */
@RestController
@RequestMapping("/about/info/rest")
public class ErpInfoRestController {
    @Autowired
    private ERPInfoService erpInfoService;

    private Logger logger = Logger.getLogger(ErpInfoRestController.class);

    @RequestMapping("/get-base-info")
    public ERPInfo getBaseInfo(){
        logger.debug("/get-base-info");
        return erpInfoService.getInfo();
    }

    @RequestMapping("/get-tel")
    public String getTel(){
        logger.debug("/get-tel");
        return erpInfoService.getTel();
    }

    @RequestMapping("/get-intro")
    public String getIntro(){
        logger.debug("/get-intro");
        return erpInfoService.getIntro();
    }

    @RequestMapping("/get-joinus")
    public String getJoinUs(){
        logger.debug("/get-joinus");
        return erpInfoService.getJoinUs();
    }

    @RequestMapping("/get-org-struct")
    public String getStruct(){
        logger.debug("/get-org-struct");
        return erpInfoService.getOrgStruct();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(ERPInfo erpInfo){
        logger.debug("/update:" + erpInfo);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(erpInfo != null)
            erpInfoService.update(erpInfo);
        map.put("result", result);

        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/update-tel", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateTel(String tel){
        logger.debug("/update-tel: " + tel);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(tel != null)
            erpInfoService.updateTel(tel);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/update-intro", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateIntro(String intro){
        logger.debug("/update-intro: " + intro);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(intro != null)
            erpInfoService.updateIntro(intro);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/update-join", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateJoin(String join){
        logger.debug("/update-join: " + join);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(join != null)
            erpInfoService.updateJoinUs(join);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/update-org-staruct", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateOrg(String org){
        logger.debug("/update-org-staruct: " + org);
        ModelMap map = new ModelMap();
        boolean result = false;
        if(org != null)
            erpInfoService.updateOrg(org);
        map.put("result", result);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }


}
