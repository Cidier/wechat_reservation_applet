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
    * 部门表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_dept")
@ApiModel(value = "MmSysDept对象", description = "部门表")
public class MmSysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Long id;


    private String tenantId;


    private String deptNo;


    private Long parentId;


    private String parentAncestors;


    private Integer state;


    private String deptName;


    private String fullName;


    private String deptType;


    private String remark;


    private Date cTime;


    private Date mTime;


    public static final String ID = "id";

    public static final String TENANT_ID = "tenant_id";

    public static final String DEPT_NO = "dept_no";

    public static final String PARENT_ID = "parent_id";

    public static final String PARENT_ANCESTORS = "parent_ancestors";

    public static final String STATE = "state";

    public static final String DEPT_NAME = "dept_name";

    public static final String FULL_NAME = "full_name";

    public static final String DEPT_TYPE = "dept_type";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_sys_dept";

    public static final String ID_OF_TABLE = "mm_sys_dept.id";

    public static final String TENANT_ID_OF_TABLE = "mm_sys_dept.tenant_id";

    public static final String DEPT_NO_OF_TABLE = "mm_sys_dept.dept_no";

    public static final String PARENT_ID_OF_TABLE = "mm_sys_dept.parent_id";

    public static final String PARENT_ANCESTORS_OF_TABLE = "mm_sys_dept.parent_ancestors";

    public static final String STATE_OF_TABLE = "mm_sys_dept.state";

    public static final String DEPT_NAME_OF_TABLE = "mm_sys_dept.dept_name";

    public static final String FULL_NAME_OF_TABLE = "mm_sys_dept.full_name";

    public static final String DEPT_TYPE_OF_TABLE = "mm_sys_dept.dept_type";

    public static final String REMARK_OF_TABLE = "mm_sys_dept.remark";

    public static final String C_TIME_OF_TABLE = "mm_sys_dept.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_dept.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_dept.id",
            "mm_sys_dept.tenant_id",
            "mm_sys_dept.dept_no",
            "mm_sys_dept.parent_id",
            "mm_sys_dept.parent_ancestors",
            "mm_sys_dept.state",
            "mm_sys_dept.dept_name",
            "mm_sys_dept.full_name",
            "mm_sys_dept.dept_type",
            "mm_sys_dept.remark",
            "mm_sys_dept.c_time",
            "mm_sys_dept.m_time"
    };


}
