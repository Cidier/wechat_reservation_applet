package com.yj.reservation.config.resolver;

import com.yj.reservation.common.security.bean.LoginInfo;
import com.yj.reservation.common.security.util.LoginInfoUtil;
import com.yj.reservation.common.util.LoggerUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Slf4j
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(CurrentUser.class);
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter
            , ModelAndViewContainer modelAndView, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("进入CurrentUser");
        HttpServletRequest httpServletRequest=webRequest.getNativeRequest(HttpServletRequest.class);
        LoginInfo loginInfo = LoginInfoUtil.send(httpServletRequest);
        if(null == loginInfo) LoggerUtil.warn("---------------用户未登录---------------");
        return loginInfo;
    }

}