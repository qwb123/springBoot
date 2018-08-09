package com.qwb.demo.filter;

import com.qwb.demo.annotation.JsonFilter;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * 在springMVC中返回到页面前可以通过实现HandlerMethodReturnValueHandler接口，
 * 在方法handleReturnValue中可以通过MethodParameter类扫描是否含有@JsonFieldFilter 注解，
 * 如果有则过滤返回对象指定的字段
 *
 * @author qwb
 */

public class JsonReturnHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {

    //支持JsonFilter注解
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(JsonFilter.class);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        JsonFilterSerializer jsonFilterSerializer = new JsonFilterSerializer();
        //如果有JsonFilter注解，则返回过滤的的对象
        if (methodParameter.hasMethodAnnotation(JsonFilter.class)) {
            JsonFilter jsonFilter = methodParameter.getMethodAnnotation(JsonFilter.class);
            //调用过滤
            jsonFilterSerializer.filter(jsonFilter.type() == null ? o.getClass() : jsonFilter.type(), jsonFilter.include(), jsonFilter.filter());
        }
        //获取HttpServletResponse返回json
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        System.out.println(jsonFilterSerializer.toJson(o));
        response.getWriter().write(jsonFilterSerializer.toJson(o));
    }


}
