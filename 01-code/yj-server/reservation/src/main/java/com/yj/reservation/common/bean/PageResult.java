package com.yj.reservation.common.bean;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

  private long total;
  private long current;
  private long pages;
  private long size;

  private List<T> records;
}
