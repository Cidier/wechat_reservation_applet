package com.yj.reservation.common.security.bean;

import lombok.Data;

@Data
public class LoginParam {

    public static final String TYPE_PHONE = "phone";
    public static final String TYPE_PASS = "pass";
    public static final String TYPE_DEVICE = "device";

    private String username;
    private String password;

    //类型：phone、pass、device

    private String type;

    private String phCode;//验证码登录
    private String deCode;//设备码登录：wxCode

    /**
     * 登录方式主要分为四种：
     *      1.后台登录
     *      2.手机号与密码登录
     *      3.手机号与验证码登录
     *      4.手机号与终端设备登录    ->该方式主要是已经登录过，此时平台只需要验证手机号和终端设备是否一致即可。
     */
}
