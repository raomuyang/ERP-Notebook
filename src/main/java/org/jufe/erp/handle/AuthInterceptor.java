package org.jufe.erp.handle;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.Policy;
import org.jufe.erp.entity.Role;
import org.jufe.erp.entity.User;
import org.jufe.erp.service.auth.RolePolicyService;
import org.jufe.erp.service.auth.TokenService;
import org.jufe.erp.service.auth.UserRoleService;
import org.jufe.erp.utils.anno.AuthRequest;
import org.jufe.erp.utils.enums.AuthLevel;
import org.jufe.erp.utils.enums.RequestEnum;
import org.jufe.erp.utils.enums.StandardStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Raomengnan on 2016/9/22.
 * 对用户操作权限鉴定在此处进行
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePolicyService rolePolicyService;
    @Autowired
    private TokenService tokenService;
    private Logger logger = Logger.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        HandlerMethod handlerMethod = null;
//        Method method = null;
//        try {
//            handlerMethod = (HandlerMethod) o;
//            method = handlerMethod.getMethod();
//        }catch (Exception e){
//            return true;
//        }
//
//        boolean auth = authRequest(method, httpServletRequest, httpServletResponse);
//        if(!auth)
//            forbidden(httpServletResponse);
//        else {
//            String token = httpServletRequest.getHeader(StandardStr.TOKEN.s());;
//            User user = tokenService.getUser(token);
//            httpServletRequest.setAttribute(StandardStr.USER.s(), user);
//        }
//        return auth;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    private void forbidden(HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(403);
    }

    private boolean authRequest(Method method, HttpServletRequest request, HttpServletResponse response){
        logger.info("Auth user request");
        AuthRequest authRequest = method.getAnnotation(AuthRequest.class);
        if(authRequest == null)
            return true;

        String token = request.getHeader(StandardStr.TOKEN.s());
        if(token == null){
            response.addHeader("msg", "Lack of credentials");
            return false;
        }

        User user = tokenService.getUser(token);
        boolean eLevel = false;
        if(user == null){
            response.addHeader("msg", "Token invalid, please reauth");
            return false;
        }
        else {
            List<Role> roles = userRoleService.getValidRoles(user.getId());
            if(roles == null)
                return false;
            for (Role r: roles){
                if(r.getLevel() >= authRequest.level().l()){
                    //管理员有超级权限
                    if(r.getLevel() == AuthLevel.ADMIN.l())
                        return true;
                    eLevel = true;//满足等级要求
                    break;
                }
            }

            if(!eLevel){
                response.addHeader("msg", "Low level");
                return false;
            }

            //如果是普通用户等级的鉴权，到这一步便可判断成功
            if(authRequest.level() == AuthLevel.USER)
                return true;

            //更敏感的权限鉴定
            List<Policy> policies = null;
            for(Role r: roles){
                List<Policy> ps = rolePolicyService.getValidPolicyBeforDate(r.getId(), new Date(System.currentTimeMillis()));
                policies.addAll(policies);
            }

            String requestPath = request.getRequestURI();
            boolean auth = false;
            //此处日后用搜索算法优化
            for (Policy p: policies){
                if(requestPath.contains(p.getId())){
                    Map<RequestEnum, Boolean> reauestAuth = p.getAuth();
                    RequestEnum m = RequestEnum.toCase( request.getMethod());
                    if(m == null){
                        logger.error("请求方式转换失败:" + request.getMethod());
                        response.addHeader("msg", "Unknow case");
                        return false;
                    }

                    return reauestAuth.get(m);
                }
            }

            response.addHeader("msg", "Lack of permission");
            return false;
        }
    }

}