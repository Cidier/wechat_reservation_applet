package com.yj.reservation.common.security.annotation;

import com.yj.reservation.common.security.annotation.entity.SecurityEntity;

import java.lang.annotation.*;

//作用域
@Target({ElementType.METHOD, ElementType.TYPE})
//生命周期
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Security {

  String code();

  String model() default SecurityEntity.MODEL_VALIDATE_CHAIN;
}
