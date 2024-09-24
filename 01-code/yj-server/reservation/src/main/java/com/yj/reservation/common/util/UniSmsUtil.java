package com.yj.reservation.common.util;

import com.apistd.uni.Uni;
import com.apistd.uni.UniException;
import com.apistd.uni.UniResponse;
import com.apistd.uni.sms.UniMessage;
import com.apistd.uni.sms.UniSMS;

import java.util.HashMap;
import java.util.Map;

public class UniSmsUtil {

    public static String ACCESS_KEY_ID = "QSXJXta1GYPKaZnyu6SzxnDnsCdPhUxP8yStbY67MsNe7pmTy";
    private static String ACCESS_KEY_SECRET = "-";

    public static final void sendSms(String phone, String code){
        // 初始化
//        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET); // 若使用简易验签模式仅传入第一个参数即可
        Uni.init(ACCESS_KEY_ID);

        // 设置自定义参数 (变量短信)
        Map<String, String> templateData = new HashMap<>();
        templateData.put("code", code);

        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phone) //这里需要 +86xxxx
//                .setSignature("UniSMS")
                .setTemplateId("pub_verif_short")
                .setTemplateData(templateData);

        // 发送短信
        try {
            UniResponse res = message.send();
            System.out.println(res);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }
    }

    public static void main(String[] args) {

        UniSmsUtil.sendSms("+8618661830391", "6666");
    }
}
