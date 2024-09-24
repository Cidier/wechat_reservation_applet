package com.yj.reservation.common.util;

import java.util.Arrays;

public class PhoneUtil {

    public static void main(String[] args) {
        System.out.println(isMobileNumber("12344475233"));
    }
    public static boolean isMobileNumber(String number) {
        String regex = "^1[3-9]\\d{9}$";
        return number.matches(regex);
    }
    public static boolean isMobileNumber2(String number) {
        if (number.length() != 11) {
            return false;
        }

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        String prefix = number.substring(0, 3);
        return prefix.equals("130") || prefix.equals("131") || prefix.equals("132") || prefix.equals("133") ||
                prefix.equals("134") || prefix.equals("135") || prefix.equals("136") || prefix.equals("137") ||
                prefix.equals("138") || prefix.equals("139") || prefix.equals("145") || prefix.equals("147") ||
                prefix.equals("149") || prefix.equals("150") || prefix.equals("151") || prefix.equals("152") ||
                prefix.equals("153") || prefix.equals("155") || prefix.equals("156") || prefix.equals("157") ||
                prefix.equals("158") || prefix.equals("159") || prefix.equals("166") || prefix.equals("167") ||
                prefix.equals("170") || prefix.equals("171") || prefix.equals("172") || prefix.equals("178") ||
                prefix.equals("180") || prefix.equals("181") || prefix.equals("182") || prefix.equals("183") ||
                prefix.equals("184") || prefix.equals("187") || prefix.equals("188") || prefix.equals("189") ||
                prefix.equals("198");
    }
    public static boolean isMobileNumber3(String number) {
        if (number.length() != 11) {
            return false;
        }

        char[] digits = number.toCharArray();
        for (char digit : digits) {
            if (!Character.isDigit(digit)) {
                return false;
            }
        }

        String prefix = number.substring(0, 3);
        return Arrays.asList("130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "145", "147",
                "149", "150", "151", "152", "153", "155", "156", "157", "158", "159", "166", "167",
                "170", "171", "172", "178", "180", "181", "182", "183", "184", "187", "188", "189",
                "198").contains(prefix);
    }
}
