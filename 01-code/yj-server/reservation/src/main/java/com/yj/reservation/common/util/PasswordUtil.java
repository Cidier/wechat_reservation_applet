package com.yj.reservation.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码强度校验
 */
public class PasswordUtil {
    /**
     * 密码必须包含大写、小写、数字和特殊字符，且长度是8位以上
     */
    private static final String PWD_REGEX_3 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()=_+;':,.?]).{8,}$";
    /**
     * 密码必须包含大写、小写、数字和特殊字符，且长度是8-32位
     */
    private static final String PWD_REGEX_2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()=_+;':,.?]).{8,32}$";

    /**
     * 密码是8-16位字母和数字的组合
     */
    private static final String PWD_REGEX_1 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    /**
     * 密码复杂度校验
     * @param password 密码
     * @param regex 强度
     * @return 校验密码强度是否合格 true/false
     */
    public static boolean isStrongPassword(String password, String regex) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        return password.matches(regex);
    }
    /**
     * 密码复杂度校验
     * @param password 密码
     * @return 校验密码强度是否合格 true/false
     */
    public static boolean isStrongPassword(String password) {
        return isStrongPassword(password, PWD_REGEX_1);
    }

    public static void main(String[] args) {
//        System.out.println(PasswordUtil.isStrongPassword(userParam.getPassword()));
    }
}
