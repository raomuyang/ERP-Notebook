package org.jufe.erp.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
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
                if(target.equals(UserRestController.class))
                    targetId = ((User) args[0]).getId();
                else
                    targetId = ((UserInfo) args[0]).getUserId();
            }

            HttpServletRequest request = (HttpServletRequest) args[args.length - 2];
            String token = request.getHeader(StandardStr.TOKEN.s());
            if(token == null)
                code = 401;

            User user = tokenService.getUser(token);
            if(user == null || !user.getId().equals(targetId))
                code = 401;

            if(code == 401){
                throw new Exception();
            }

        }catch (Exception e){
            switch (code){
                case 401:
                    throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "TOKEN INVALID");
                case 403:
                    throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "EXCESS OF AUTHORITY OPTION");
                default:
                    throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
            }
        }

    }


}
