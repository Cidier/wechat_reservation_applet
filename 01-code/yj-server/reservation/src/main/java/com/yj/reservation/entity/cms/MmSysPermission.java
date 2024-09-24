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
    * 权限表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_permission")
@ApiModel(value = "MmSysPermission对象", description = "权限表")
public class MmSysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Long id;


    private String tenantId;


    private String name;


    private String type;


    private Integer od;


    private Long parentId;


    private String url;


    private String routeUrl;


    private Integer state;


    private String icon;


    private String parentIds;


    private String precode;


    private Date cTime;


    private Date mTime;


    public static final String ID = "id";

    public static final String TENANT_ID = "tenant_id";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String OD = "od";

    public static final String PARENT_ID = "parent_id";

    public static final String URL = "url";

    public static final String ROUTE_URL = "route_url";

    public static final String STATE = "state";

    public static final String ICON = "icon";

    public static final String PARENT_IDS = "parent_ids";

    public static final String PRECODE = "precode";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_sys_permission";

    public static final String ID_OF_TABLE = "mm_sys_permission.id";

    public static final String TENANT_ID_OF_TABLE = "mm_sys_permission.tenant_id";

    public static final String NAME_OF_TABLE = "mm_sys_permission.name";

    public static final String TYPE_OF_TABLE = "mm_sys_permission.type";

    public static final String OD_OF_TABLE = "mm_sys_permission.od";

    public static final String PARENT_ID_OF_TABLE = "mm_sys_permission.parent_id";

    public static final String URL_OF_TABLE = "mm_sys_permission.url";

    public static final String ROUTE_URL_OF_TABLE = "mm_sys_permission.route_url";

    public static final String STATE_OF_TABLE = "mm_sys_permission.state";

    public static final String ICON_OF_TABLE = "mm_sys_permission.icon";

    public static final String PARENT_IDS_OF_TABLE = "mm_sys_permission.parent_ids";

    public static final String PRECODE_OF_TABLE = "mm_sys_permission.precode";

    public static final String C_TIME_OF_TABLE = "mm_sys_permission.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_permission.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_permission.id",
            "mm_sys_permission.tenant_id",
            "mm_sys_permission.name",
            "mm_sys_permission.type",
            "mm_sys_permission.od",
            "mm_sys_permission.parent_id",
            "mm_sys_permission.url",
            "mm_sys_permission.route_url",
            "mm_sys_permission.state",
            "mm_sys_permission.icon",
            "mm_sys_permission.parent_ids",
            "mm_sys_permission.precode",
            "mm_sys_permission.c_time",
            "mm_sys_permission.m_time"
    };


}
