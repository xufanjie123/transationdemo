package com.fanjiexu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create By Hong Cui
 * 2018-4-24
 */
public class InterfaceTimingInterceptor implements HandlerInterceptor {
    private long startTime;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        startTime=System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long endTime=System.currentTimeMillis();
        long t=endTime-startTime;
        System.out.println(httpServletRequest.getRequestURI()+"耗时:"+t+"ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long endTime=System.currentTimeMillis();
        long t=endTime-startTime;
        System.out.println(httpServletRequest.getRequestURI()+"after耗时:"+t+"ms");
    }
}
