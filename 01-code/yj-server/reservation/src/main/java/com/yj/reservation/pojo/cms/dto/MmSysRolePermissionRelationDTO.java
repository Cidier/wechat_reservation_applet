package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysRolePermissionRelationDTO implements Serializable {

    private Long roleId;

    private Long permissionId;

    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";
}

