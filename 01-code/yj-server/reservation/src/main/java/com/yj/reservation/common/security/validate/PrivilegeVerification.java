package com.yj.reservation.common.security.validate;

import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.redis.RedisService;
import com.yj.reservation.common.security.validate.bean.PrivilageEntity;
import com.yj.reservation.common.security.validate.bean.PrivilagePermission;
import com.yj.reservation.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeVerification implements Verification {

    @Autowired(required = false)
    private RedisService redisService;

    @Override
    public JsonResult validate(PrivilageEntity security) {
        //权限维护  这里考虑RedisService处理方式
        String permissionJson = (String) redisService.get(security.getUserToken());
        if (permissionJson == null) {
            return JsonResult.fail().setMsg("没有权限-1");
        }

        List<PrivilagePermission> permissionList = JsonUtil.fromList(permissionJson, PrivilagePermission.class);
        if (permissionList == null || permissionList.size() == 0) {
            return JsonResult.fail().setMsg("没有权限-2");
        }
        for (PrivilagePermission permission : permissionList) {
            if (security.getCode().equals(permission.getPrecode())) {
                return JsonResult.success();
            }
        }
        return JsonResult.fail().setMsg("没有权限-3");
    }
}
