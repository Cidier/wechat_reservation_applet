package com.yj.reservation.common.constant.charset;

public class RedisKeysConst {
    //login:organization:list:key:
    public static final String LOGIN_ORGANIZATION_KEY = "mmyj:";

    public static final String toLoginKey(Long userId){
        return LOGIN_ORGANIZATION_KEY+userId;
    }

}
