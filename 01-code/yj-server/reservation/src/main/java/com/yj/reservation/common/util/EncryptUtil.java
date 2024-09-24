package com.yj.reservation.common.util;


import com.yj.reservation.common.constant.charset.CharSetConst;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//http://aub.iteye.com/blog/1129339
//http://www.cnblogs.com/shoubianxingchen/p/5869373.html
//http://blog.csdn.net/hbcui1984/article/details/5201247
//http://blog.csdn.net/qq_18870023/article/details/52183755

/*
 * 用于对称加密
 * 首先应该明白AES是基于数据块的加密方式，也就是说，每次处理的数据是一块（16字节），当数据不是16字节的倍数时填充，这就是所谓的分组密码（区别于基于比特位的流密码），16字节是分组长度

 AES支持五种模式：CBC，CFB，ECB，OFB，PCBC，

 jce中实现了三种补码方式：NoPadding，PKCS5Padding，ISO10126Padding;不支持SSL3Padding,不支持“NONE”模式。

 ECB：是一种基础的加密方式，密文被分割成分组长度相等的块（不足补齐），然后单独一个个加密，一个个输出组成密文。
 CBC：是一种循环模式，前一个分组的密文和当前分组的明文异或操作后再加密，这样做的目的是增强破解难度。必须加初始化向量
 CFB/OFB实际上是一种反馈模式，目的也是增强破解的难度。
 ECB和CBC的加密结果是不一样的，两者的模式不同，而且CBC会在第一个密码块运算时加入一个初始化向量。
 * 
 */
public class EncryptUtil {
  private Cipher enCipher = null;
  private Cipher deCipher = null;

  public EncryptUtil(String key) {
    try {
      //下面注释代码生成随机数密钥在不同平台可能不一致，所以说会出现解密失败的情况
//      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//      keyGenerator.init(128, new SecureRandom(key.getBytes()));
//      SecretKey secretKey = keyGenerator.generateKey();
//      ECB不需要iv
//      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//      random.setSeed(key.getBytes());
//      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//      keyGenerator.init(128, random);
//      SecretKey secretKey = keyGenerator.generateKey();
      SecretKey secretKey = new SecretKeySpec(key.getBytes(CharSetConst.UTF_8), "AES");

      enCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      deCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      IvParameterSpec spec = null;
      enCipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
      deCipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
    } catch (Exception e) {
      throw new RuntimeException("init aescoder failed", e);
    }
  }

  public static void sqwe() throws Exception {
    EncryptUtil encryptUtil = new EncryptUtil("ac");
    byte[] abc = encryptUtil.enCipher.doFinal("123".getBytes());
    String c = new String(encryptUtil.deCipher.doFinal(abc));
    System.out.println(c);
  }

  private byte[] encrypt(byte[] data) throws Exception {
    return enCipher.doFinal(data);
  }

  private byte[] decrpty(byte[] data) throws Exception {
    return deCipher.doFinal(data);
  }

  // 加密字符串(本质上是加密字符串的字节数组)，得到加密后的字节数组，转换成十六进制字符返回
  public String encrypt(String data) throws Exception {
    byte[] bytes = data.getBytes(CharSetConst.UTF_8);
    byte[] b = encrypt(bytes);
    return new String(HexUtil.encode(b));
  }

  // 解密字符串(传递的参数字符串必须是十六进制字符串)，首先把十六进制字符串转换成对应的字节数组，然后对该字节数组解密，得到原始字节数组，还原得到原始字符串
  public String decrypt(String data) throws Exception {
    byte[] b = decrpty(HexUtil.decode(data));
    return new String(b, CharSetConst.UTF_8);
  }

  public static void main(String[] args) throws Exception {
//    System.out.println(System.getProperties());
//    System.out.println(System.getenv());
    EncryptUtil aesCoder = new EncryptUtil("8rZeUUpoZ1SvyP^W");
//    byte[] bytes = aesCoder.encrypt("testAppId".getBytes());
//    String result = new String(Base64.encodeBase64(bytes));
//    System.out.println(result);
//
//    byte[] s = Base64.decodeBase64(result.getBytes());
//    byte[] sd = aesCoder.decrpty(s);
//    System.out.println(new String(sd));
    String str = aesCoder.encrypt("周大生付士大夫举例说");
    String str1 = aesCoder.decrypt("b069c048e7b1605ebf64edba3c91ad18");
    System.out.println(str);
    System.out.println(str1);
//    sqwe();
  }

//  public EncryptUtil1(String key) {
//    try {
//      //下面注释代码生成随机数密钥在不同平台可能不一致，所以说会出现解密失败的情况
////      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
////      keyGenerator.init(128, new SecureRandom(key.getBytes()));
////      SecretKey secretKey = keyGenerator.generateKey();
//
//      byte[] raw = key.getBytes("utf-8");
//      SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//      enCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//      deCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//      IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
//      enCipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//      deCipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//    } catch (Exception e) {
//      throw new RuntimeException("init aescoder failed", e);
//    }
//  }

}
