package com.yj.reservation.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * IO读写操作工具类
 */
public class StreamUtil {
  private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);
  private static final int DEFAULT_BUFFER_SIZE = 1024;

  public static String stream2str(InputStream is, String encoder) throws Exception {
    ByteArrayOutputStream bos = null;
    String data = null;
    try {
      bos = new ByteArrayOutputStream();
      is2os(is, bos);
      data = bos.toString(encoder);
    } finally {
      closeStream(bos);
      closeStream(is);
    }
    return data;
  }

  public static void is2os(InputStream bis, OutputStream bos) throws Exception {
    byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
    while (true) {
      int b = bis.read(buf, 0, DEFAULT_BUFFER_SIZE);
      if (b == -1)
        break;
      bos.write(buf, 0, b);
    }
  }

  public static byte[] is2Bytes(InputStream bis) throws Exception {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    is2os(bis, bos);
    return bos.toByteArray();
  }

  public static void closeStream(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (IOException e) {
        logger.error("close stream error:", e);
      }
    }
  }

//	private static final IOUtilCommon IO_UTIL_BEAN = new IOUtilCommon();
//
//	/**
//	 * 根据将file对象读取file文件中的数据，并返回. 这里需要注意：file文件要提前创建完成。
//	 * 
//	 * @param file
//	 * @return
//	 */
//	public static final String in(File file) throws IOException {
//		return IO_UTIL_BEAN.in(file);
//	}
//
//	/**
//	 * 根据将data数据存放在file文件中. 这里需要注意：file文件要提前创建完成。
//	 * 
//	 * @param file
//	 * @param data
//	 * @throws IOException
//	 */
//	public static final void out(File file, String data) throws IOException {
//		IO_UTIL_BEAN.out(file, data);
//	}
//
//	/**
//	 * 根据将data数据存放在file文件中. 这里需要注意：file文件要提前创建完成。
//	 * 
//	 * @param file
//	 * @param data
//	 * @throws IOException
//	 */
//	public static final void out(File file, byte[] data) throws IOException {
//		IO_UTIL_BEAN.out(file, data);
//	}
//
//	/**
//	 * 可通过IOUtil 类调用当前类的方法 如IOUtil类无法满足需求，如：需线程安全情况下，可自行创建该类，调用方法
//	 * 
//	 * @author miaoxy
//	 *
//	 */
//	static class IOUtilCommon {
//
//		/**
//		 * 根据将file对象读取file文件中的数据，并返回. 这里需要注意：file文件要提前创建完成。
//		 * 
//		 * @param file
//		 * @return
//		 * @throws IOException
//		 */
//		public String in(File file) throws IOException {
//			InputStreamReader reader = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));
//			char[] c = new char[2048];
//			int len = -1;
//			StringBuffer sb = new StringBuffer();
//			while ((len = reader.read(c)) != -1) {
//				sb.append(new String(c, 0, len));
//			}
//
//			return sb.toString();
//		}
//
//		/**
//		 * 根据将data数据存放在file文件中. 这里需要注意：file文件要提前创建完成。
//		 * 
//		 * @param file
//		 * @param data
//		 * @throws IOException
//		 */
//		public void out(File file, String data) throws IOException {
//			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
//			writer.write(data);
//			writer.flush();
//			writer.close();
//		}
//
//		/**
//		 * 根据将data数据存放在file文件中. 这里需要注意：file文件要提前创建完成。
//		 * 
//		 * @param file
//		 * @param data
//		 * @throws IOException
//		 */
//		public void out(File file, byte[] data) throws IOException {
//			FileOutputStream writer = new FileOutputStream(file);
//			writer.write(data);
//			writer.flush();
//			writer.close();
//		}
//
//	}

}
