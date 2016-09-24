package org.jufe.erp.handle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Raomengnan on 2016/9/24.
 * ModleAdnView必须是一个new ModleAndView，否则前端看到的状态码就会是500（null）或404(modle有其它属性)
 */
@Component
public class UncactchedExceptionResolver extends AbstractHandlerExceptionResolver{
    Logger logger = Logger.getLogger(UncactchedExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        HandlerMethod handlerMethod = null;
        Logger resolverELog = null;
        try {
            handlerMethod = (HandlerMethod) o;
            resolverELog = Logger.getLogger(handlerMethod.getBeanType());
        }catch (Exception ex){
            logger.error(ex);
            resolverELog = logger;
        }


        if(HttpClientErrorException.class.isAssignableFrom(e.getClass())){
            resolverELog.info(e.getMessage());
            catchHttpClientError(httpServletResponse, e);
        }
        else {
            resolverELog.error(e.getMessage());
            catchError(httpServletResponse, e);
        }

        return new ModelAndView();
    }

    private void catchHttpClientError(HttpServletResponse response, Exception e){
        HttpClientErrorException httpClientErrorException = (HttpClientErrorException) e;
        response.setStatus(httpClientErrorException.getStatusCode().value());
        response.setHeader("msg", e.getMessage());

    }

    private void catchError(HttpServletResponse response, Exception e){
        response.setStatus(500);
        response.setHeader("msg", e.getMessage());

    }

}
