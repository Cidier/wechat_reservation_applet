package com.yj.reservation.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;


/**
 *
 *  javax.servlet.http.HttpServletRequest 与
 *  jakarta.servlet.http.HttpServletRequest
 *  Apache Tomcat 9 和 10 本质上是相同的产品。一种使用 javax.* 包命名，另一种使用 jakarta.* 命名。这两种产品是并行开发的。
 *      包命名的变化是 Oracle 将 Java EE 技术责任移交给 Eclipse 基金会的过渡的一部分，并在该基金会更名为Jakarta EE。
 *      如果您选择采用新的命名方式，您可能只需更改import声明即可。可能有一些工具可以帮助您完成此操作，包括最新 IDE 中的新功能。同样，文章和视频非常丰富。
 * @author quzhi- miao -up 改为
 * @create 2020-04-20 19:44
 * @update 2023-12-22 09:42
 */
public class CookieUtil {
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void clearCookie(String name, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
//    cookie.setDomain(domain);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static void writeCookie(String name, String value, HttpServletRequest request, HttpServletResponse response) {
//    StringBuffer url = request.getRequestURL();
//    System.out.println("url:" + url);
//    System.out.println("uri:" + request.getRequestURI());
//    System.out.println("servr:" + request.getServerName());
//    System.out.println("port:" + request.getServerPort());
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
//    cookie.setDomain(tempContextUrl);
        //单位是S
        cookie.setMaxAge(60 * 60 * 24 * 90);
        response.addCookie(cookie);
    }
}
