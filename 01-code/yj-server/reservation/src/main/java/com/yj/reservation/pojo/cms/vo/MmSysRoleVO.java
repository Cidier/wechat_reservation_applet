package com.yj.reservation.pojo.cms.vo;

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
public class MmSysRoleVO implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String remarks;

    @ApiModelProperty("状态：0关闭角色 1开启角色")
    private Integer state;

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("修改时间")
    private Date mTime;

    private Long[] permissionIds;

    public static final String ID = "id";

    public static final String TENANT_ID = "tenant_id";

    public static final String NAME = "name";

    public static final String REMARKS = "remarks";

    public static final String STATUS = "status";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

