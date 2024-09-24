package com.yj.reservation.common.util;

import com.yj.reservation.common.constant.charset.CharSetConst;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * 文件工具类
 *
 * @author miaoxy
 */
public class FileUtil {

  //	private static final FileUtilCommon fileUtilBean = new FileUtilCommon();
//  Files.setPosixFilePermissions
  public static final String FILE_SEPARATOR = System.getProperty("file.separator");
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

  public static boolean uploadImg(MultipartFile file, String path, String fileName){
    String realPath = path+fileName;
    File dest = new File(realPath);
    try{
      file.transferTo(dest);
      return true;
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }
  public static List<String> readLines(final String data) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));
    final List<String> list = new ArrayList<>();
    String line = reader.readLine();
    while (line != null) {
      list.add(line);
      line = reader.readLine();
    }
    return list;
  }

  public static List<String> readLinesFromFile(String filepath, String encoder) throws Exception {
    File file = new File(filepath);
    if (!file.exists()) {
      return null;
    }
    List<String> lines = FileUtils.readLines(new File(filepath), encoder);
    return lines;
  }

  public static String readDataFromFile(String filepath, String encoder) throws Exception {
    File file = new File(filepath);
    if (!file.exists()) {
      return null;
    }
    return FileUtils.readFileToString(new File(filepath), encoder);
  }

  public static byte[] readFileToByteArray(String filepath) throws Exception {
    File file = new File(filepath);
    if (!file.exists()) {
      return null;
    }
    return FileUtils.readFileToByteArray(file);
  }


  public static void writeBytesToFile(String filepath, byte[] bytes, boolean append) throws Exception {
    FileUtils.writeByteArrayToFile(new File(filepath), bytes, append);
  }

  public static void writeDataToFile(String filepath, String data, boolean append) throws Exception {
    writeDataToFile(filepath, data, CharSetConst.UTF_8, append);
  }

  public static void writeDataToFile(String filepath, String data, String charset, boolean append) throws Exception {
    FileUtils.writeStringToFile(new File(filepath), data, charset, append);
  }

  public static <K, V> Map<K, V> readMapFromFile(String filepath, String encoder, Class<K> K, Class<V> V)
      throws Exception {
    return readMapFromFile(filepath, encoder, false, K, V);
  }

  public static <K, V> Map<K, V> readMapFromFile(String filepath, String encoder, boolean order, Class<K> K,
                                                 Class<V> V) throws Exception {
    Map<K, V> map = order ? new LinkedHashMap<>() : new HashMap<>();
    List<String> lines = readLinesFromFile(filepath, encoder);
    if (lines != null) {
      try {
        for (String line : lines) {
          String[] values = line.split("=");
          String k = values[0];
          String v = null;
          if (values.length == 2) {
            v = values[1];
          } else {
            String tmp = "";
            for (int i = 1; i < values.length; i++) {
              tmp += values[i] + (i == values.length - 1 ? "" : "=");
            }
            v = tmp;
          }
          K key = (K) StrictConvertUtils.getInstance().convert(k, K);
          V value = (V) StrictConvertUtils.getInstance().convert(v, V);
          map.put(key, value);
        }
      } catch (Exception e) {
        logger.error("file is illegal map:", e);
        throw e;
      }
    }
    return map;
  }

  private static class StrictConvertUtils extends ConvertUtilsBean {
    private static StrictConvertUtils INSTANCE = new StrictConvertUtils();

    private StrictConvertUtils() {
      register(true, true, 0);
    }

    public static StrictConvertUtils getInstance() {
      return INSTANCE;
    }
  }

  public static void writeMapToFile(String filepath, Map<?, ?> map, String encoder, boolean append) throws Exception {
    if (map != null) {
      StringBuilder buf = new StringBuilder();
      for (Map.Entry<?, ?> entry : map.entrySet()) {
        buf.append(entry.getKey()).append("=").append(entry.getValue()).append(LINE_SEPARATOR);
      }
      if (buf.length() > 0) {
        writeDataToFile(filepath, buf.toString(), encoder, append);
      }
    }
  }

  public static <T> Set<T> readSetFromFile(String filepath, String encoder, Class<T> T) throws Exception {
    return readSetFromFile(filepath, encoder, false, T);
  }

  public static <T> Set<T> readSetFromFile(String filepath, String encoder, boolean order, Class<T> T)
      throws Exception {
    Set<T> set = order ? new LinkedHashSet<T>() : new HashSet<T>();
    List<String> lines = readLinesFromFile(filepath, encoder);
    if (lines != null) {
      try {
        for (String line : lines) {
          T t = (T) StrictConvertUtils.getInstance().convert(line, T);
          set.add(t);
        }
      } catch (Exception e) {
        logger.error("file is illegal set:", e);
        throw e;
      }
    }
    return set;
  }

  public static void writeSetToFile(String filepath, Set<String> set, String encoder, boolean append)
      throws Exception {
    if (set != null) {
      StringBuilder buf = new StringBuilder();
      for (String line : set) {
        buf.append(line).append(LINE_SEPARATOR);
      }
      if (buf.length() > 0) {
        writeDataToFile(filepath, buf.toString(), encoder, append);
      }
    }
  }

  // 写入字节数组到指定文件
  // private static void writeByteToFile(String filepath, byte[] bytes,
  // boolean append) throws Exception {
  // File file = new File(filepath);
  // File p = file.getParentFile();
  // if (!p.exists()) {
  // p.mkdirs();
  // }
  // if (!append) {
  // file.delete();
  // }
  //
  // RandomAccessFile raf = null;
  // try {
  // raf = new RandomAccessFile(filepath, "rw");
  // if (append) {
  // raf.seek(raf.length());
  // }
  // raf.write(bytes);
  // } finally {
  // StreamUtils.closeStream(raf);
  // }
  // }

  public static String getSuffix(String filePath) {
    return getSuffix(new File(filePath));
  }

  public static String getSuffix(File file) {
    return getNameAndSuffix(file, true, true)[1];
  }

  /**
   * @param file
   * @return
   */
  public static String getNameWithoutSuffix(File file) {
    return getNameAndSuffix(file, true, true)[0];
  }

  /**
   * needName以及needSuffix为true情况下，如果没有文件名或者没有后缀，会抛出异常，为false情况下，则会返回null
   *
   * @param file
   * @param needName
   * @param needSuffix
   * @return
   */
  public static String[] getNameAndSuffix(File file, boolean needName, boolean needSuffix) {
    String fileName = file.getName();
    int index = fileName.lastIndexOf(".");
    String name = null;
    String suffix = null;
    if (index >= 0) {
      name = fileName.substring(0, index);
      suffix = fileName.substring(index);
    } else {
//      if (mustHasSuffix) {
//        throw new IllegalStateException(file.getAbsolutePath() + "没有后缀");
//      } else {
//        return fileName;
//      }
      name = fileName;
    }
    if (needName && StringUtils.isEmpty(name)) {
      throw new IllegalStateException(file.getAbsolutePath() + "没有文件名");
    }
    if (needSuffix && StringUtils.isEmpty(suffix)) {
      throw new IllegalStateException(file.getAbsolutePath() + "没有后缀");
    }
    return new String[]{name, suffix};
  }


  public static boolean deleteFile(String filePath) throws Exception {
    File file = new File(filePath);
    return deleteFile(file);
  }

  /**
   * 删除文件，文件不存在返回true，文件存在并且是一个文件删除成功，返回true
   * 文件存在并且是一个文件删除失败，返回false
   * 文件是一个目录抛出异常
   *
   * @param file
   * @return
   * @throws Exception
   */
  public static boolean deleteFile(File file) throws IllegalArgumentException {
    if (file.exists()) {
      if (file.isDirectory()) {
        throw new IllegalArgumentException(file.getAbsolutePath() + " existed as one directory");
      } else {
        return file.delete();
      }
    } else {
      return true;
    }
  }

  public static void deleteDir(String dirPath) throws Exception {
    deleteDir(new File(dirPath));
  }

  public static void deleteDir(File dir) throws Exception {
    FileUtils.deleteDirectory(dir);
  }

  public static void copyFile(String srcFile, String destFile) throws Exception {
    copyFile(new File(srcFile), new File(destFile));
  }
  public static void copyFile(File srcFile, File destFile) throws Exception {
    FileUtils.copyFile(srcFile, destFile);
  }

  // 给定路径如果父目录不存在就创建
  public static boolean createParentDir(String filePath) {
    File file = new File(filePath);
    return createParentDir(file);
  }

  public static boolean createParentDir(File file) {
    File parentFile = file.getParentFile();
    return createDir(parentFile);
  }

  public static boolean createDir(String filePath) {
    return createDir(new File(filePath));
  }

  public static boolean createDir(File file) {
    if (!file.exists()) {
      return file.mkdirs();
    } else if (file.isDirectory()) {
      return true;
    } else {
      throw new IllegalArgumentException(file.getName() + "existed as one file");
    }
  }

  public static boolean renameFile(File srcFile, File destFile) {
    if (srcFile.renameTo(destFile)) {
      return true;
    }
    try {
      FileUtils.copyFile(srcFile, destFile);
      if (srcFile.delete()) {
        return true;
      } else {
        destFile.delete();
        return false;
      }
    } catch (Exception e) {
      logger.error("rename file but copy file failed:", e);
    }
    return false;
  }


  public static void main(String[] args) throws Exception {
//		writeDataToFile("C:\\Users\\Dev2\\Desktop\\xxx\\1.txt", "123", true);
//    System.out.println(new Date(1548917003235L).toLocaleString());
    deleteDir("C:\\Users\\Dev2\\Desktop\\new  3sdf.txt");
  }


}
