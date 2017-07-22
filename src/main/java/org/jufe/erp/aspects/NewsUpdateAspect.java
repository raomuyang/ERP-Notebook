package org.jufe.erp.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jufe.erp.controller.rest.news.NewsRestController;
import org.jufe.erp.entity.News;
import org.jufe.erp.entity.NewsImage;
import org.jufe.erp.entity.Role;
import org.jufe.erp.entity.User;
import org.jufe.erp.service.auth.UserRoleService;
import org.jufe.erp.service.news.NewsImageService;
import org.jufe.erp.service.news.NewsService;
import org.jufe.erp.utils.enums.AuthLevel;
import org.jufe.erp.utils.enums.StandardStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/24.
 */
@Aspect
@Component
public class NewsUpdateAspect {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private NewsImageService newsImageService;

    @Pointcut("execution(* org.jufe.erp.controller.rest.news..update*(..))")
    void update(){}

    @Pointcut("execution(* org.jufe.erp.controller.rest.news..delete*(..))")
    void delete(){}

    @Pointcut("execution(* org.jufe.erp.controller.rest.news..upload*(..))")
    void upload(){}

    @Before("update()")
    void authBeforeUpdate(JoinPoint joinPoint){
        Class target = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String targetId = null;

        try {
            Logger logger = Logger.getLogger(target);

            if(target.equals(NewsRestController.class))
                targetId = ((News) args[0]).getId();
            else
                targetId = ((NewsImage)args[0]).getNewsId();
            News news = newsService.findById(targetId);
            if(news == null){
                logger.error("Case news error" + args[0]);
                return;
            }

            HttpServletRequest request = (HttpServletRequest) args[args.length - 1];
            User user = (User) request.getAttribute(StandardStr.USER.s());
            if(user.getId().equals(news.getAuthorId()))
                return;

            List<Role> roles = userRoleService.getValidRoles(user.getId());
            for (Role r : roles)
                if(r.getLevel() > AuthLevel.CONTROLLER.l()){
                    logger.info(r.getRoleName() + String.format(" operation[%s]:", user.getId()) + methodName);
                    return;
                }

            logger.info(user.getId() + "[No Authority to alter]" + news.getId());
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No Authority to alter");
        }catch (Exception e){
            throw e;
        }


    }

    @Before("delete()")
    void authBeforeDelete(JoinPoint joinPoint){
        Class target = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String targetId = null;

        try {
            Logger logger = Logger.getLogger(target);
            if(methodName.contains("Url")){
                String url = String.valueOf(args[0]);
                NewsImage newsImage = newsImageService.getByUrl(url);
                targetId = newsImage.getUserId();
            }
            else {
                String newsId = String.valueOf(args[0]);
                News news = newsService.findById(newsId);
                if (news != null)
                    targetId = news.getAuthorId();
            }

            HttpServletRequest request = (HttpServletRequest) args[args.length - 1];
            User user = (User) request.getAttribute(StandardStr.USER.s());
            if( targetId.equals(user.getId()))
                    return;

            List<Role> roles = userRoleService.getValidRoles(user.getId());
            for (Role r : roles)
                if(r.getLevel() > AuthLevel.CONTROLLER.l()){
                    logger.info(r.getRoleName() + String.format(" operation[%s]:", user.getId()) + methodName);
                    return;
                }

            logger.info(user.getId() + "[No Authority to alter]" + targetId);
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "No Authority to alter");

        }catch (Exception e){
            throw e;
        }

    }

    @Before("upload()")
    void authBeforeUpload(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if(args.length < 3)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Param Error");
        NewsImage newsImage = null;
        HttpServletRequest request;
        User user = null;
        try {
            newsImage = (NewsImage) args[0];
            request = (HttpServletRequest) args[2];
            user = (User) request.getAttribute(StandardStr.USER.s());
        }catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Param Error, please check it");
        }

        News news = newsService.findById(newsImage.getNewsId());
        if(news == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "News doesn't exist");
        if(user== null || !user.getId().equals(news.getAuthorId())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "ILLEGAL OPERATION");
        }

    }
}
