package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 数据源配置表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysDatasourceDTO implements Serializable {

    private Long id;//走这里

    @ApiModelProperty("状态：1启用，0禁止")
    private Integer state;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("驱动类")
    private String driverClass;

    @ApiModelProperty("连接地址")
    private String url;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("备注")
    private String remark;

    private Date cTime;//走这里

    @ApiModelProperty("修改时间")
    private Date mTime;

    public static final String ID = "id";

    public static final String STATE = "state";

    public static final String NAME = "name";

    public static final String DRIVER_CLASS = "driver_class";

    public static final String URL = "url";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

