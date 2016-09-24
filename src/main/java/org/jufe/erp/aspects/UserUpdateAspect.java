package org.jufe.erp.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jufe.erp.controller.rest.user.UserRestController;
import org.jufe.erp.entity.User;
import org.jufe.erp.entity.UserInfo;
import org.jufe.erp.service.auth.TokenService;
import org.jufe.erp.utils.enums.StandardStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Raomengnan on 2016/9/22.
 */
@Aspect
@Component
public class UserUpdateAspect {

    @Pointcut("execution(* org.jufe.erp.controller.rest.user..update*(..))")
    void pointcut(){}


    @Autowired
    private TokenService tokenService;
    private Logger log = Logger.getLogger(UserUpdateAspect.class);

    @Before("pointcut()")
    public void beforeUpdateAuth(JoinPoint joinPoint){
        Class target = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String targetId = null;

        int code = 200;
        try {
            if(methodName.contains("ById"))
                targetId = (String) args[0];
            else {
                if(target.equals(UserRestController.class)){
                    User u = ((User) args[0]);
                    targetId = u == null ? null: u.getId();
                }

                else{
                    UserInfo uinfo = ((UserInfo) args[0]);
                    targetId = uinfo == null ? null: uinfo.getUserId();
                }
            }
        }catch (Exception e){
            log.error("获取targetId失败：" + args);
            return;
        }


        HttpServletRequest request = (HttpServletRequest) args[args.length - 2];

        //一般情况下不会出现401的错误
        User user = (User) request.getAttribute(StandardStr.USER.s());
        if(user == null )
            code = 401;
        else if(!user.getId().equals(targetId))
            code = 403;


        switch (code) {
            case 401:
                throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "TOKEN INVALID");
            case 403:
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "EXCESS OF AUTHORITY OPTION");
            default:return;
        }

    }


}
