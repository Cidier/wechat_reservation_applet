package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysDeptDTO implements Serializable {

    private Long id;//走这里

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("组织机构编号")
    private String deptNo;

    @ApiModelProperty("上级组织机构ID")
    private Long parentId;

    @ApiModelProperty("公司ID序列")
    private String parentAncestors;

    @ApiModelProperty("组织机构状态")
    private Integer state;

    @ApiModelProperty("组织机构名称")
    private String deptName;

    @ApiModelProperty("组织机构全称")
    private String fullName;

    @ApiModelProperty("组织机构类型")
    private String deptType;

    @ApiModelProperty("备注")
    private String remark;

    private Date cTime;//走这里

    @ApiModelProperty("修改时间")
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
}

