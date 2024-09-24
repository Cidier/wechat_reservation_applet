package com.yj.reservation.config.interceptor;

import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.security.annotation.OpenApi;
import com.yj.reservation.common.security.bean.LoginInfo;
import com.yj.reservation.common.security.util.LoginInfoUtil;
import com.yj.reservation.common.util.LoggerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户验证拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //进入controller方法前调用

        String requestUrl = request.getRequestURI();
        LoggerUtil.info("进入Authentication拦截器: "+requestUrl);
        /**
         * 在这里判断是否登录，并且通过@OpenApi来进行设置是否开放不需要权限等认证即可访问
         */

        if(handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            if(h.getMethodAnnotation(OpenApi.class) != null && h.getMethodAnnotation(OpenApi.class).code()){
                //说明当前方法不需要登录也可以访问
                System.out.println("用户想执行的操作是:"+h.getMethodAnnotation(OpenApi.class).code());

                //将当前登录的登录对象，放入Map中，以备后续使用
                LoginInfoUtil.setCurrent(LoginInfoUtil.send(request));

                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }


        //判断是否登录
        LoginInfo loginInfo = LoginInfoUtil.send(request);
        if(null == loginInfo){
            LoggerUtil.warn("---------------用户未登录---------------");
//            throw new RuntimeException("请先登录系统！ token is Not null!");
//            return false;

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(JsonResult.failNotLogin().setMsg("未登录或登录过期！"));
            return false;
        }

        //将当前登录的登录对象，放入Map中，以备后续使用
        LoginInfoUtil.setCurrent(loginInfo);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //进入controller方法后调用

        //删除当前请求的储存登录对象
        LoginInfoUtil.removeCurrent();

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
