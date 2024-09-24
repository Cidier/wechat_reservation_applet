package com.yj.reservation.common.security.validate.bean;

import lombok.Data;

@Data
public class PrivilagePermission {
  private Integer id;

  private String name;

  private String type;

  private Integer od;

  private Integer parentId;

  private String url;

  private Integer status;

  private String icon;

  private String parentIds;

  private String precode;

  public PrivilagePermission() {
  }

  public PrivilagePermission(Integer id, String name, String type, Integer od, Integer parentId, String url, Integer status, String icon, String parentIds, String precode) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.od = od;
    this.parentId = parentId;
    this.url = url;
    this.status = status;
    this.icon = icon;
    this.parentIds = parentIds;
    this.precode = precode;
  }

}
