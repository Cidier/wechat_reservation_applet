package com.yj.reservation.common.bean;

import lombok.Data;

/**
 * 分页查询基类
 */
@Data
public class BasePageQuery<T> {

    private int pageNo;
    private int pageSize;
}