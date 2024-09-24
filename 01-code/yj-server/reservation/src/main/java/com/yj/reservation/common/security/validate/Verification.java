package com.yj.reservation.common.security.validate;

import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.security.validate.bean.PrivilageEntity;

public interface Verification {

  JsonResult validate(PrivilageEntity security);
}
