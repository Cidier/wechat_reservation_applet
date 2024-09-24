package com.yj.reservation.common.security.util;

import com.yj.reservation.common.util.EncryptUtil;

import java.util.Random;

public class HexTokenUtil {

    private static final String HEX_KEY = "abc1231123YjDES1";

    private static final String REDIS_TK_USERID_PREFIX = "tk:yj:";

    public static final String getRedisTkUserId(Long userId) {
        int i = new Random().nextInt(899999) + 100000;
        return REDIS_TK_USERID_PREFIX + userId + ":" + i;
    }
    public static final String getRedisTkUserId(String loginInfoKey) {
        return REDIS_TK_USERID_PREFIX + loginInfoKey;
    }

    public static final String getLogKeyToRedisTk(String redisKey){
        String[] keys = redisKey.split(REDIS_TK_USERID_PREFIX);
        return keys[1];
    }

    private static EncryptUtil encryptUtil = new EncryptUtil(HEX_KEY);

    public static final String encryptToken(String str) throws Exception {
        return encryptUtil.encrypt(str);
    }

    public static final String decryptToken(String encryptStr) throws Exception {
        return encryptUtil.decrypt(encryptStr);
    }

}
