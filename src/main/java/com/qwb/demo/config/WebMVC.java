package com.qwb.demo.config;

import com.qwb.demo.filter.JsonReturnHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMVC extends WebMvcConfigurationSupport {

    @Autowired
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Bean
    public HandlerMethodReturnValueHandler getJsonReturnHandler() {
        return new JsonReturnHandler();
    }


    //把jsonfilter加到拦截器的执行链中
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(getJsonReturnHandler());
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        // 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);
    }


    //强行吧自定义返回字段添加到返回器中
    @PostConstruct
    public void init() {
        final List<HandlerMethodReturnValueHandler> originalHandlers = new ArrayList<>(
                requestMappingHandlerAdapter.getReturnValueHandlers());

        final int deferredPos = obtainValueHandlerPosition(originalHandlers, DeferredResultMethodReturnValueHandler.class);
        // Add our handler directly after the deferred handler.
        originalHandlers.add(deferredPos + 1, getJsonReturnHandler());

        requestMappingHandlerAdapter.setReturnValueHandlers(originalHandlers);
    }

    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }

}
