package com.yj.reservation.common.security.util;

import com.yj.reservation.common.redis.RedisService;
import com.yj.reservation.common.security.bean.LoginInfo;
import com.yj.reservation.common.security.constant.SecurityConst;
import com.yj.reservation.common.util.CookieUtil;
import com.yj.reservation.common.util.JsonUtil;
import com.yj.reservation.common.util.LoggerUtil;
import com.yj.reservation.common.util.SpringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class LoginInfoUtil {

    /**
     * 插入和获取 redis中的 logininfo数据
     *
     * send(HttpServletRequest request)
     * send(Integer userId)
     * add(LoginInfo info)
     */

    public static final LoginInfo send(HttpServletRequest request) throws Exception {
//        String token = CookieUtil.getCookieValue(request, SecurityConst.TOEKN_COOKIE_NAME);
        String token = request.getHeader("Authorization");
        LoggerUtil.info("Token: " + token);
        if(token == null){
//            throw new RuntimeException("请先登录系统！ token is Not null!");
            LoggerUtil.warn("请先登录系统！ token is Not null!");
            return null;
        }
        //这里根据Authorization判断是 终端用户，还是 后台用户
        try {
            token = HexTokenUtil.decryptToken(token);
            System.out.println(token);
        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("请先登录系统！ token is Not null!");
            LoggerUtil.warn("请先登录系统！ token is Not null!!");
            return null;
        }
        String logKey ;
        //这里根据Authorization判断是 终端用户，还是 后台用户
        try {
            logKey = HexTokenUtil.getLogKeyToRedisTk(token);
            System.out.println(logKey);
        } catch (Exception e) {
            LoggerUtil.warn("请先登录系统！ logKey is Not null!!");
            return null;
        }
        return send(logKey);
    }
    public static final LoginInfo send(String logKey) throws Exception {
        Object obj = SpringUtil.getBean(RedisService.class).get(logKey);
        if(obj == null){
            return null;
        }
        return JsonUtil.fromJson((String) obj, LoginInfo.class);
    }

    public static final String add(LoginInfo info){
        String logKey = toLoginKey(info.getUserId(), info.getSysTag());
        info.setLogKey(logKey);
        SpringUtil.getBean(RedisService.class).set(logKey, JsonUtil.toJsonString(info), 86400l);// 6小时21600 24 86400
        return logKey;
    }

    //login:user:info:key:
    //yu:jiang:login:user
    public static final String LOGIN_USER_INFO_KEY = "yjlu:";

    public static final String toLoginKey(Long userId, String sysTag){
        int i = new Random().nextInt(899999) + 100000;
        return LOGIN_USER_INFO_KEY+userId+sysTag+i;
    }

    private static final Map<String, LoginInfo> currentLoginInfoMap = new HashMap<>();

    public static LoginInfo getCurrent() {
        //通过当前线程找到对应的LoginInfo
        System.out.println("获取LoginInfo: " + Thread.currentThread().getName());

        //null
        System.out.println("获取LoginInfo2: " +  currentLoginInfoMap.get(Thread.currentThread().getName()));
        return currentLoginInfoMap.get(Thread.currentThread().getName());
    }
    public static void setCurrent(LoginInfo loginInfo) {
        System.out.println("存储LoginInfo: " + Thread.currentThread().getName());
        currentLoginInfoMap.put(Thread.currentThread().getName(), loginInfo);
    }
    public static void removeCurrent(){
        System.out.println("删除LoginInfo: " + Thread.currentThread().getName());
        currentLoginInfoMap.remove(Thread.currentThread().getName());
    }
}
