package com.yj.reservation.common.security.annotation;

import java.lang.annotation.*;

/**
 * 开放API 默认false
 */
@Target({ElementType.METHOD, ElementType.TYPE})
//生命周期
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenApi {


    boolean code() default false; //默认false，为内部，默认不加也是false
}
