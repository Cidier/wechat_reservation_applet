package com.yj.reservation.common.security;

import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.security.annotation.Security;
import com.yj.reservation.common.security.annotation.entity.SecurityEntity;
import com.yj.reservation.common.security.constant.SecurityConst;
import com.yj.reservation.common.security.util.HexTokenUtil;
import com.yj.reservation.common.security.validate.Verification;
import com.yj.reservation.common.security.validate.bean.PrivilageEntity;
import com.yj.reservation.common.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Security 注解，加上后，才会校验接口权限，如果不加默认不校验
 *
 */
@Aspect
@Order(1)
@Configuration
public class SecurityAOP {
    @Autowired
    private ValidateChain validateChain;
    @Pointcut("@annotation(com.yj.reservation.common.security.annotation.Security)")
    public void excudeService() {
    }
 
    @After("excudeService()")
    public void after() {
//        System.out.print("我是after"); //最后执行
    }
 
    @Around("excudeService()&&@annotation(security)")
    public Object around(ProceedingJoinPoint joinPoint, Security security) {
        System.out.println("权限验证" + security.code());//调用方法前、
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);


        //从cookie中获取使用token。
//        String token = CookieUtil.getCookieValue(request, SecurityConst.TOEKN_COOKIE_NAME);
//        Cookie[] cookies = request.getCookies();
//        String token = null;
//        if(cookies != null) {
//            for (Cookie cookie : cookies) {
//                LoggerUtil.info("CookieName : " + cookie.getName() + "  CookieValue : " + cookie.getValue());
//                if(SecurityConst.TOEKN_COOKIE_NAME.equals(cookie.getName())){
//                    token = cookie.getValue();
//                }
//            }
//        }

        //使用http请求头Authorization获取token
        String token = request.getHeader("Authorization");



        if(token == null){
            return JsonResult.fail().setMsg("请先登录系统-1!");
        }
        try {
            token = HexTokenUtil.decryptToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail().setMsg("请先登录系统-2!");
        }
        //需要获取userID
        if(!SecurityEntity.MODEL_NONE.equals(security.model())){
            for(Verification verification : validateChain.list()){
                PrivilageEntity entity = new PrivilageEntity();
                entity.setUserToken(token);
                entity.setCode(security.code());
                entity.setModel(security.model());
                JsonResult result = verification.validate(entity);
                if(result != null && result.getErrcode() != JsonResult.CODE_SUCCESS){
                    return result;//没有权限
                }
            }
        }

        //继续向下执行
        Object result = null;
        try {
            result = joinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("权限验证后置");//调用方法后
        return result;
    }
}