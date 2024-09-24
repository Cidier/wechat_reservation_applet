package com.yj.reservation.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 字节数组和16进制字符串的转换
 *
 * @author Dev2
 */
public class HexUtil {
  // hex负责把2进制转换成16进制
  private final static Hex hex = new Hex();

  /*
   * 把字节数组转换成字符串，如new byte[11100100, 10111000, 10101101],首先转换成对应的十六进制字节数组即new
   * byte[0xe4,0xb8,0xad]，之后转换成字符串"e4b8ad"
   */
  public static String encode(byte[] bytes) {
    return new String(hex.encode(bytes));
  }

  /*
   * 参数必须是十六进制字符串，如字符串"e4b8ad",转换成字节数组对应的十六进制是new
   * byte[0xe4,0xb8,0xad],二进制是[11100100, 10111000, 10101101]
   */
  public static byte[] decode(String data) throws DecoderException {
    return hex.decode(data.getBytes());
  }

  public static void main(String[] args) throws Exception {
//		String str = "中";
//		String abc = HexUtil.encode(str.getBytes());
//		System.out.println(abc);
//		byte[] d = HexUtil.decode(abc);
//		System.out.println(new String(d));

    EncryptUtil encryptUtil = new EncryptUtil("abc123abc123abc1");

//    String encryptPath = "E:\\eclipse_20181129_workspace\\fox\\02-sql\\fox_category_encrypt.txt";
//    String dataPath = "E:\\eclipse_20181129_workspace\\fox\\02-sql\\fox_category.txt";

    String encryptPath = "/Users/naeshinyou/Documents/3.14/fox_github/02-sql/fox_data/fox_data_category_encrypt.txt";
    String dataPath = "/Users/naeshinyou/Documents/3.14/fox_github/02-sql/fox_data/fox_data_category.sql";

//    String dataPath = "/Users/naeshinyou/Documents/3.14/fox_project/02-sql/fox_data/fox_data.sql";
//    String encryptPath = "/Users/naeshinyou/Documents/3.14/fox_project/02-sql/fox_data/fox_data_encrypt.txt";


//    String encryptPath = "D:\\project\\web\\idea\\fox\\fox\\02-sql\\fox_data\\fox_collect_union_encrypt.txt";
//    String dataPath = "D:\\project\\web\\idea\\fox\\fox\\02-sql\\fox_data\\fox_collect_union.sql";
////		sql 加 密
    String data = FileUtil.readDataFromFile(dataPath, "UTF-8");
    String coderData = encryptUtil.encrypt(data);
    FileUtil.writeDataToFile(encryptPath, coderData, "UTF-8", false);

    //sql 解 密
    String encodeData = FileUtil.readDataFromFile(encryptPath, "UTF-8");
    String decodeDataStr = encryptUtil.decrypt(encodeData);
    System.out.println(decodeDataStr);
//    FileUtil.writeDataToFile(dataPath, decodeDataStr, "UTF-8", false);
  }


}
