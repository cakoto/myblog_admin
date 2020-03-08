package com.gyf.myblog_admin.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
 * 拦截错误信息
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //控制返回一个页面+后台控制信息
    @ExceptionHandler(Exception.class)//标识这个方法用于Exception的异常处理
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //{}用占位作用，将getRequestURL()的值传到{}处
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);

        /*
         * 如果存在指定状态，抛出异常
         */
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class )!= null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());//获取url
        mv.addObject("exception", e);//获取异常信息
        mv.setViewName("error/error");//返回到哪个页面
        return mv;
    }
}
