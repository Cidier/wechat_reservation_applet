package com.yj.reservation.pojo.cms.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuItemVO implements Comparable<MenuItemVO>{

  private Long id;
  private String icon;
  private Integer index;
  private String title;
  private String url;
  private String type;
  private List<MenuItemVO> subs;


  @Override
  public int compareTo(MenuItemVO o) {
    return this.index - o.index;
  }
}
