package com.yj.reservation.entity.cms;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 数据源配置表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_datasource")
@ApiModel(value = "MmSysDatasource对象", description = "数据源配置表")
public class MmSysDatasource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")

    private Long id;


    private Integer state;


    private String name;


    private String driverClass;


    private String url;


    private String username;


    private String password;


    private String remark;


    private Date cTime;


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

    public static final String TABLE_NAME = "mm_sys_datasource";

    public static final String ID_OF_TABLE = "mm_sys_datasource.id";

    public static final String STATE_OF_TABLE = "mm_sys_datasource.state";

    public static final String NAME_OF_TABLE = "mm_sys_datasource.name";

    public static final String DRIVER_CLASS_OF_TABLE = "mm_sys_datasource.driver_class";

    public static final String URL_OF_TABLE = "mm_sys_datasource.url";

    public static final String USERNAME_OF_TABLE = "mm_sys_datasource.username";

    public static final String PASSWORD_OF_TABLE = "mm_sys_datasource.password";

    public static final String REMARK_OF_TABLE = "mm_sys_datasource.remark";

    public static final String C_TIME_OF_TABLE = "mm_sys_datasource.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_datasource.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_datasource.id",
            "mm_sys_datasource.state",
            "mm_sys_datasource.name",
            "mm_sys_datasource.driver_class",
            "mm_sys_datasource.url",
            "mm_sys_datasource.username",
            "mm_sys_datasource.password",
            "mm_sys_datasource.remark",
            "mm_sys_datasource.c_time",
            "mm_sys_datasource.m_time"
    };


}
