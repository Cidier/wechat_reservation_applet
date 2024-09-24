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
    * 角色表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_role")
@ApiModel(value = "MmSysRole对象", description = "角色表")
public class MmSysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Long id;


    private String tenantId;


    private String name;


    private String remarks;


    private Integer state;


    private Date cTime;


    private Date mTime;


    public static final String ID = "id";

    public static final String TENANT_ID = "tenant_id";

    public static final String NAME = "name";

    public static final String REMARKS = "remarks";

    public static final String STATE = "state";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_sys_role";

    public static final String ID_OF_TABLE = "mm_sys_role.id";

    public static final String TENANT_ID_OF_TABLE = "mm_sys_role.tenant_id";

    public static final String NAME_OF_TABLE = "mm_sys_role.name";

    public static final String REMARKS_OF_TABLE = "mm_sys_role.remarks";

    public static final String STATE_OF_TABLE = "mm_sys_role.state";

    public static final String C_TIME_OF_TABLE = "mm_sys_role.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_role.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_role.id",
            "mm_sys_role.tenant_id",
            "mm_sys_role.name",
            "mm_sys_role.remarks",
            "mm_sys_role.state",
            "mm_sys_role.c_time",
            "mm_sys_role.m_time"
    };


}
