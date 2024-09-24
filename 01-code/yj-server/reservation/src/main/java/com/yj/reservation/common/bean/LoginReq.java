package com.yj.reservation.common.bean;

import lombok.Data;

@Data
public class LoginReq {
 
    private String account;
 
    private String pwd;
 
    private String code;
}