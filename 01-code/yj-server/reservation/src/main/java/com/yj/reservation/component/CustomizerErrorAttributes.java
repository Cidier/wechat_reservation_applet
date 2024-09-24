package com.yj.reservation.component;

import com.yj.reservation.common.bean.JsonResult;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomizerErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> map = super.getErrorAttributes(webRequest, options);

        Object status = map.get("status");
        int code = (Integer) status;
        map.clear();
        map.put("errcode", code);
        String msg = (String) webRequest.getAttribute("businessMsg", RequestAttributes.SCOPE_REQUEST);
        if (msg == null) {
            msg = getErrorMsg(code);
        }
        map.put("msg", msg);
        return map;

    }

    private String getErrorMsg(int code) {
        String msg = null;
        if (code == JsonResult.CODE_FAIL_NOT_FOUND) {
            msg = JsonResult.CODE_FAIL_NOT_FOUND_MSG;
        } else if (code == JsonResult.CODE_FAIL_FORBID) {
            msg = JsonResult.CODE_FAIL_FORBID_MSG;
        } else if (code == JsonResult.CODE_FAIL_SERVER_ERROR) {
            msg = JsonResult.CODE_FAIL_SERVER_ERROR_MSG;
        } else {
            msg = JsonResult.CODE_FAIL_UNKNOWN_MSG;
        }
        return msg;
    }
}