package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysRoleDTO implements Serializable {

    private Long id;//走这里

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String remarks;

    @ApiModelProperty("状态：0关闭角色 1开启角色")
    private Integer state;

    private Integer permissionId;

    private Long[] permissionIds;

    private Date cTime;//走这里

    @ApiModelProperty("修改时间")
    private Date mTime;

}

