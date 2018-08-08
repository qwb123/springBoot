package com.qwb.demo.config;


import com.qwb.demo.filter.JsonReturnHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMVC implements WebMvcConfigurer {

    @Bean
    public JsonReturnHandler getJsonReturnHandler() {
        return new JsonReturnHandler();
    }

    //把jsonfilter加到拦截器的执行链中
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(getJsonReturnHandler());
    }

    //拦截的url路径
/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/**");
    }*/

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        // 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);
    }


}
