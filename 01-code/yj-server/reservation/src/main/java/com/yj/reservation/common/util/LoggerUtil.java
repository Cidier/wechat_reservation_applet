package com.yj.reservation.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shkstart
 * @create 2019-02-28 14:44
 */
public class LoggerUtil {
  //  private static final Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
  private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

  private static String getMsg(String msg) {
    //Thread.currentThread().getStackTrace()性能相比较Throwable会稍差
//    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
    StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
    return "[" + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "():" + stackTraceElement.getLineNumber() + "]: " + msg;
  }

  public static void error(String msg) {
    logger.error(getMsg(msg));
  }

  public static void error(String msg, Throwable e) {
    logger.error(getMsg(msg) + ":", e);
  }

  public static void warn(String msg) {
    logger.warn(getMsg(msg));
  }

  public static void warn(String msg, Throwable e) {
    logger.warn(getMsg(msg), e);
  }

  public static void info(String msg) {
    logger.info(getMsg(msg));
  }

  public static void info(String msg, Throwable e) {
    logger.info(getMsg(msg), e);
  }

  public static void debug(String msg) {
    logger.debug(getMsg(msg));
  }

  public static void debug(String msg, Throwable e) {
    logger.debug(getMsg(msg), e);
  }
}
