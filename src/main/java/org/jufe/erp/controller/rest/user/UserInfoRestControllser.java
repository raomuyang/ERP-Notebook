package org.jufe.erp.controller.rest.user;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Raomengnan on 2016/9/21.
 */
@RestController
@RequestMapping("/rest/user/info")
public class UserInfoRestControllser {

    @Autowired
    private UserInfoService userInfoService;
    private Logger logger = Logger.getLogger(UserInfoRestControllser.class);

    @RequestMapping("/get-by-userid/{userId}")
    public UserInfo getByUserId(@PathVariable("userId") String userId){
        logger.debug("get-by-userid/" + userId);
        return userInfoService.findByUserId(userId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody UserInfo info){
        logger.debug("update:" + info);
        boolean result = false;
        ModelMap map = new ModelMap();
        if(info == null || info.getUserId() == null)
            map.put("msg", "参数错误，请检查");
        else {
            result = userInfoService.update(info);
            if(!result)
                map.put("msg", "更新失败，请检查后重试");
        }
        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ModelMap> delete(@RequestBody String userId){
        logger.debug("delete:" + userId);
        boolean result = false;
        ModelMap map = new ModelMap();
        if(userId == null || userId.isEmpty())
            map.put("msg", "参数错误，请检查");
        else {
            List res = userInfoService.deleteByUserId(userId);
            if(res == null)
                map.put("msg", "删除出错，请检查后重试");
            else
                result = true;
        }

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }
}
