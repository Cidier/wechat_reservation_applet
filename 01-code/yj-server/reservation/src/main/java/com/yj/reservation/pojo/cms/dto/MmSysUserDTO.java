package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysUserDTO implements Serializable {

    private Long id;//走这里

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("用户状态")
    private String state;

    @ApiModelProperty("用户类型")
    private String type;

    @ApiModelProperty("用户登录名称")
    private String userName;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户密码")
    private String userPassword;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("描述")
    private String remarks;

    private Date cTime;//走这里

    @ApiModelProperty("修改时间")
    private Date mTime;

    private Long[] roleIds;
}

