package org.jufe.erp.handle;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by rao-mengnan on 2017/7/23.
 * 在Spring-boot中手动指定拦截器
 */
@Configuration
public class HandlerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/rest/**");
    }
}
