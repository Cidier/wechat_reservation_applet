package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 角色用户关联表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysUserRoleRelationDTO implements Serializable {

    private Long userId;

    private Long roleId;

    public static final String USER_ID = "user_id";

    public static final String ROLE_ID = "role_id";
}

