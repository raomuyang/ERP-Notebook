package org.jufe.erp.controller.rest.user;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.TokenInfo;
import org.jufe.erp.entity.User;
import org.jufe.erp.entity.UserRole;
import org.jufe.erp.service.auth.TokenService;
import org.jufe.erp.service.auth.UserRoleService;
import org.jufe.erp.service.user.UserService;
import org.jufe.erp.utils.DateTools;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.StandardStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/20.
 */
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRoleService userRoleService;

    private Logger logger = Logger.getLogger(UserRestController.class);

    @RequestMapping("/check-exist/{id}")
    public boolean check(@PathVariable("id") String id){
        logger.debug("/check-exist/"+ id);
        User user = userService.findById(id);
        return user != null;
    }


    @RequestMapping("/info/{id}")
    public User getById(@PathVariable("id") String id){
        logger.debug("/get/"+ id);
        User user = userService.findById(id);
        if(user != null)
            user.setPwd(null);
        return user;
    }


    @RequestMapping("/info/list/name/{name}")
    public List<User> getByName(@PathVariable("name") String name){
        logger.debug("/get-by-name/"+ name);
        List<User> users = userService.findByName(name);
        users.forEach(user -> {
            user.setPwd(null);
        });

        return users;
    }

    @RequestMapping("/info/list/grade/{grade}")
    public List<User> getByGrade(@PathVariable("grade") int grade){
        logger.debug("/get-by-grade/"+ grade);
        List<User> users = userService.findByGrade(grade);
        users.forEach(user -> {
            user.setPwd(null);
        });
        return users;
    }

    @RequestMapping("/info/list/location/{location}")
    public List<User> getByLocation(@PathVariable("location") String location){
        logger.debug("/get-by-name/"+ location);
        List<User> users = userService.findByLocation(location);
        users.forEach(user -> {
            user.setPwd(null);
        });

        return users;
    }

    @AuthRequest
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> update(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        logger.debug("update:" + user);
        boolean result = false;
        ModelMap map = new ModelMap();

        result = userService.update(user);
        if(!result)
            map.put("msg", "更新失败，请检查后重试:" + user);

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest
    @RequestMapping(value = "/update-username", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateUsername(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        logger.debug("update username:" + user);
        boolean result = false;
        ModelMap map = new ModelMap();

        result = userService.updateUserName(user);
        if(!result)
            map.put("msg", "更新失败，请检查后重试" + user);

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest
    @RequestMapping(value = "/update-pwd", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updatePwd(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        logger.debug("update pwd:" + user);
        boolean result = false;
        ModelMap map = new ModelMap();

        User u = (User) request.getAttribute(StandardStr.USER.s());
        user.setId(u.getId());
        result = userService.updateUserPwd(user);
        if(!result)
            map.put("msg", "更新失败，请检查后重试:" + user);

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest
    @RequestMapping(value = "/update-location", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateLocation(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        logger.debug("update location:" + user);
        boolean result = false;
        ModelMap map = new ModelMap();

        result = userService.updateLocation(user);
        if(!result)
            map.put("msg", "更新失败，请检查后重试:" + user);

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest
    @RequestMapping(value = "/update-head", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateHeadById(String userId, MultipartFile imageFile, HttpServletRequest request, HttpServletResponse response){
        logger.debug("update:" + userId);
        boolean result = false;
        ModelMap map = new ModelMap();

        result = userService.updateUserHead(userId, imageFile);
        if(!result)
            map.put("msg", "更新失败，请检查后重试:" + userId);

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> regist(User user, MultipartFile imageFile, HttpServletRequest request){
        logger.debug("/regist"+ user == null ? user: user.getId());
        boolean result = false;
        ModelMap map = new ModelMap();

        if(!check(user.getId()))
            result = userService.addUser(user, imageFile);

        if(!result) {
            user.setPwd("**************");
            map.put("msg", "当前用户已存在:" + user.getId());
        }
        else {
            UserRole userRole = new UserRole();
            userRole.setRoleId("user");
            userRole.setUserId(user.getId());
            userRole.setTermD(DateTools.getDateAfter(
                    new Date(System.currentTimeMillis()), 999999999));
            userRoleService.saveUserRole(userRole);
        }

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    /**
     * 登录成功返回token
     * @param user
     * @return {reslut:true/false, token "xxxxxx"}
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> login(@RequestBody User user, HttpServletResponse response){

        logger.debug("login:" + user);
        ModelMap map = new ModelMap();
        boolean result = false;

        if(user != null){
            User u = userService.checkLogin(user.getId(), user.getPwd());
            if(u != null) {
                result = true;
                TokenInfo tokenInfo = tokenService.create(user.getId());
                map.put("token", tokenInfo.getToken());
                Cookie cookie = new Cookie("token", tokenInfo.getToken());
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
            }
        }

        map.put("result", result);
        return new ResponseEntity<ModelMap>(map, HttpStatus.OK);
    }

    @AuthRequest
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> logout(HttpServletRequest request){
        logger.debug(String.format("logout[%s]", request.getHeader(StandardStr.TOKEN.s())));
        boolean res = tokenService.delete(request.getHeader(StandardStr.TOKEN.s()));
        ModelMap map = new ModelMap();
        HttpStatus status = HttpStatus.OK;
        map.put("result", res);
        if(!res)
            status = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<ModelMap>(map, status);
    }

}
