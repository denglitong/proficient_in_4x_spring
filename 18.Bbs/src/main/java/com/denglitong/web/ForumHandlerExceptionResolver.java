package com.denglitong.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 *
 * @author litong.deng@foxmail.com
 * @date 2021/7/11
 */
public class ForumHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.setAttribute("ex", ex);
        ex.printStackTrace();
        return super.doResolveException(request, response, handler, ex);
    }
}
