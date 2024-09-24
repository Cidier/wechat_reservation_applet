package com.yj.reservation.common.bean;

import com.yj.reservation.common.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {

  private int errcode;
  private String msg;
  private Map<String, Object> data = new HashMap<>();

  public static final int CODE_NOT_LOGIN = 9;
  public static final int CODE_SUCCESS = 0;

  /**
   * 业务逻辑错误
   */
  public static final int CODE_FAIL_SERVICE_ERROR = 1;
  public static final int CODE_FAIL_FORBID = 403;
  public static final int CODE_FAIL_NOT_FOUND = 404;
  public static final int CODE_FAIL_SERVER_ERROR = 500;

  public static final String CODE_FAIL_FORBID_MSG = "相关数据无权限访问";
  public static final String CODE_FAIL_NOT_FOUND_MSG = "相关数据不存在";
  public static final String CODE_FAIL_SERVER_ERROR_MSG = "服务器内部异常";
  public static final String CODE_FAIL_UNKNOWN_MSG = "未知错误";

  public static final String MSG_INVALID_PARAM = "非法参数";

  private JsonResult() {

  }

  private JsonResult(int errcode) {
    this.errcode = errcode;
  }

  private JsonResult(int errcode, String msg) {
    this.errcode = errcode;
    this.msg = msg;
  }

  public static JsonResult setErrcode(int code) {
    return new JsonResult(code);
  }

  public static JsonResult success() {
    return new JsonResult(CODE_SUCCESS);
  }

  public static JsonResult fail() {
    return new JsonResult(CODE_FAIL_SERVICE_ERROR);
  }

  public static JsonResult failNotLogin(){
    return new JsonResult(CODE_NOT_LOGIN);
  }

  public int getErrcode() {
    return errcode;
  }

  public String getMsg() {
    return msg;
  }

  public JsonResult setMsg(String msg) {
    this.msg = msg;
    return this;
  }
  public Map<String, Object> getData() {
    return data;
  }

  public JsonResult put(String key, Object value) {
    this.data.put(key, value);
    return this;
  }

  @Override
  public String toString() {
    try {
      return JsonUtil.toJsonString(this);
    } catch (Exception e) {
    }
    return "{\"errcode\":" + CODE_FAIL_SERVICE_ERROR + ",\"msg\":\"返回结果序列化失败:" + this.msg + "\"}";
  }
}
