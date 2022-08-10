package com.example.demo.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomHandlerInterceptor implements HandlerInterceptor {

    private static Log log = LogFactory.getLog(CustomHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Handlerメソッドが呼び出される前に行う処理を実装する
        //log.debug("preHandle : {} {} {}" , request, request, handler);
        log.debug("preHandle start");

        // Handlerメソッドを呼び出す場合はtrueを返却する
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        // Handlerメソッドが正常終了した後に行う処理を実装する(例外が発生した場合は、このメソッドは呼び出されない)
        //log.debug("postHandle : {} {} {} {}" , request, response, handler, modelAndView);
        log.debug("postHandle start");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Handlerメソッドの呼び出しが完了した後に行う処理を実装する(例外が発生しても、このメソッドは呼び出される)
        log.debug("afterCompletion start");
    }
}
