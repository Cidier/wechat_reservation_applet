package com.yj.reservation.common.security.validate.bean;

import lombok.Data;

@Data
public class PrivilageEntity {

  private String userToken;
  private String code;
  private String model;

}
