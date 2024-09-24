package com.yj.reservation.common.util;

import com.yj.reservation.common.constant.charset.CharSetConst;
import org.apache.commons.codec.Charsets;

public class Base64Util {
  final private static char[] b64Array = "z_-ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxy"
      .toCharArray();

  public static CharSequence encodeToStr(String text, int factor) {
    return encode(text.getBytes(Charsets.toCharset(CharSetConst.UTF_8)), factor);
  }

  public static CharSequence encode(byte[] bytes, int factor) {
    StringBuilder buf = new StringBuilder();
    encode(buf, bytes, factor);
    return buf;
  }

  private static void encode(StringBuilder buf, byte[] bytes, int factor) {
    int len = bytes.length / 3;
    int i = 0;
    for (i = 0; i < len; i++) {
      int b1 = bytes[i * 3] & 0xFF;
      if (factor > 0) {
        b1 = (b1 ^ ((i + 1) * factor)) & 0xFF;
      }

      int b2 = bytes[i * 3 + 1] & 0xFF;
      if (factor > 0) {
        b2 = (b2 ^ ((i + 1) * factor)) & 0xFF;
      }

      int b3 = bytes[i * 3 + 2] & 0xFF;
      if (factor > 0) {
        b3 = (b3 ^ ((i + 1) * factor)) & 0xFF;
      }

      int n1 = b1 >>> 2; // [111111]11,11111111,11111111
      int n2 = ((b1 & 0x03) << 4) + (b2 >>> 4); // 111111[11,1111]1111,11111111
      int n3 = ((b2 & 0x0F) << 2) + (b3 >>> 6);// 11111111,1111[1111,11]111111
      int n4 = b3 & 0x3F; // 11111111,11111111,11[111111]

      buf.append(b64Array[n1]).append(b64Array[n2]).append(b64Array[n3])
          .append(b64Array[n4]);
    }

    int mod = bytes.length % 3;
    if (mod == 1) {
      int b1 = bytes[len * 3] & 0xFF;
      if (factor > 0) {
        b1 = (b1 ^ ((i + 1) * factor)) & 0xFF;
      }

      int n1 = b1 >>> 2; // [111111]11
      int n2 = b1 & 0x03; // 111111[11]
      buf.append(b64Array[n1]).append(b64Array[n2]);
    } else if (mod == 2) {
      int b1 = bytes[len * 3] & 0xFF;
      if (factor > 0) {
        b1 = (b1 ^ ((i + 1) * factor)) & 0xFF;
      }

      int b2 = bytes[len * 3 + 1] & 0xFF;
      if (factor > 0) {
        b2 = (b2 ^ ((i + 1) * factor)) & 0xFF;
      }

      int n1 = b1 >>> 2; // [111111]11,11111111
      int n2 = ((b1 & 0x03) << 4) + (b2 >>> 4); // 111111[11,1111]1111
      int n3 = b2 & 0x0F;// 11111111,1111[1111]

      buf.append(b64Array[n1]).append(b64Array[n2]).append(b64Array[n3]);
    }
  }

  private static int getCharValue(char c) {
    if (c >= 'A' && c <= 'Z') {
      return 3 + (c - 'A');
    } else if (c >= 'a' && c <= 'y') {
      return 39 + (c - 'a');
    } else if (c >= '0' && c <= '9') {
      return 29 + (c - '0');
    } else if (c == '-') {
      return 2;
    } else if (c == '_') {
      return 1;
    }

    return 0;
  }

  public static String decodeToStr(CharSequence text, int factor) {
    byte[] bytes = decode(text, factor);
    return new String(bytes, Charsets.toCharset(CharSetConst.UTF_8));
  }

  private static byte[] decode(CharSequence text, int factor) {
    int len = text.length() / 4;
    int mod = text.length() % 4;
    int add = 0;
    if (mod == 1) {
      throw new IllegalArgumentException("the length of text is illegal");
    } else if (mod == 2) {
      add = 1;
    } else if (mod == 3) {
      add = 2;
    }

    byte[] bytes = new byte[3 * len + add];
    int i = 0;
    for (i = 0; i < len; i++) {
      int n1 = getCharValue(text.charAt(i * 4));
      int n2 = getCharValue(text.charAt(i * 4 + 1));
      int n3 = getCharValue(text.charAt(i * 4 + 2));
      int n4 = getCharValue(text.charAt(i * 4 + 3));

      // [111111]11,11111111,11111111
      // 111111[11,1111]1111,11111111
      // 11111111,1111[1111,11]111111
      // 11111111,11111111,11[111111]
      int b1 = (n1 << 2) + (n2 >>> 4);
      if (factor > 0) {
        b1 = (b1 ^ ((i + 1) * factor)) & 0xFF;
      }

      int b2 = (n2 << 4) + (n3 >>> 2);
      if (factor > 0) {
        b2 = (b2 ^ ((i + 1) * factor)) & 0xFF;
      }

      int b3 = ((n3 & 0x03) << 6) + n4;
      if (factor > 0) {
        b3 = (b3 ^ ((i + 1) * factor)) & 0xFF;
      }

      bytes[i * 3] = (byte) b1;
      bytes[i * 3 + 1] = (byte) b2;
      bytes[i * 3 + 2] = (byte) b3;
    }

    if (add == 1) {
      int n1 = getCharValue(text.charAt(len * 4));
      int n2 = getCharValue(text.charAt(len * 4 + 1));

      // [111111]11
      // 111111[11]
      int b1 = (n1 << 2) + n2;
      if (factor > 0) {
        b1 = (b1 ^ ((i + 1) * factor)) & 0xFF;
      }

      bytes[len * 3] = (byte) b1;
    } else if (add == 2) {
      int n1 = getCharValue(text.charAt(len * 4));
      int n2 = getCharValue(text.charAt(len * 4 + 1));
      int n3 = getCharValue(text.charAt(len * 4 + 2));

      // [111111]11,11111111
      // 111111[11,1111]1111
      // 11111111,1111[1111]
      int b1 = (n1 << 2) + (n2 >>> 4);
      if (factor > 0) {
        b1 = (b1 ^ ((i + 1) * factor)) & 0xFF;
      }

      int b2 = (n2 << 4) + n3;
      if (factor > 0) {
        b2 = (b2 ^ ((i + 1) * factor)) & 0xFF;
      }

      bytes[len * 3] = (byte) b1;
      bytes[len * 3 + 1] = (byte) b2;
    }

    return bytes;
  }

  public static void main(String[] args) throws Exception {

    for (int i = 0; i < 10000000; i++) {
      String str = String.valueOf(i);
      String result = encodeToStr(str, 1).toString();
      result = decodeToStr(result, 1);
      if (Integer.parseInt(result) != i) {
        System.out.println(i);
      }
    }
    // BASE64Encoder encoder = new BASE64Encoder();
    // BASE64Decoder decoder = new BASE64Decoder();
    // String result = encoder.encode(str.getBytes());

    // byte[] abc = decoder.decodeBuffer(result);
    // result = new String(abc);
    // System.out.println(result);
  }
}
