package com.yj.reservation.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * 单向加密
 *
 * @author Dev2
 */
public class MessageDigestUtil {
  private static final Logger logger = LoggerFactory.getLogger(MessageDigestUtil.class);
  private final static MessageDigestUtil instance = new MessageDigestUtil();
  private MessageDigest md5Digest = null;

  public static MessageDigestUtil getInstance() {
    return instance;
  }

  private MessageDigestUtil() {
    try {
      md5Digest = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
      logger.error("md5 init failed:", e);
    }
  }

  // 计算字符串的字节数组的信息摘要，并以16进制字符串返回
  public String encoderMD5(String data) {
    byte[] bytes = md5Digest.digest(data.getBytes());
    return HexUtil.encode(bytes);
  }

  public static void main(String[] args) {
    long time = System.currentTimeMillis();
    time += 1000 * 60 * 60 * 24 * 30;
    String oid = "passwd" + ":" + time + ":" + "abc123";
    String str = MessageDigestUtil.instance.encoderMD5(oid);
    System.out.println(str);

  }
}
