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
    * 租户表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_tenant")
@ApiModel(value = "MmSysTenant对象", description = "租户表")
public class MmSysTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Long id;


    private String tenantName;


    private String tenantState;


    private String tenantType;


    private String contactEmail;


    private String contactPhone;


    private String contactPerson;


    private String domainBind;


    private Date lifespan;


    private String remarks;


    private Date cTime;


    private Date mTime;


    public static final String ID = "id";

    public static final String TENANT_NAME = "tenant_name";

    public static final String TENANT_STATE = "tenant_state";

    public static final String TENANT_TYPE = "tenant_type";

    public static final String CONTACT_EMAIL = "contact_email";

    public static final String CONTACT_PHONE = "contact_phone";

    public static final String CONTACT_PERSON = "contact_person";

    public static final String DOMAIN_BIND = "domain_bind";

    public static final String LIFESPAN = "lifespan";

    public static final String REMARKS = "remarks";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_sys_tenant";

    public static final String ID_OF_TABLE = "mm_sys_tenant.id";

    public static final String TENANT_NAME_OF_TABLE = "mm_sys_tenant.tenant_name";

    public static final String TENANT_STATE_OF_TABLE = "mm_sys_tenant.tenant_state";

    public static final String TENANT_TYPE_OF_TABLE = "mm_sys_tenant.tenant_type";

    public static final String CONTACT_EMAIL_OF_TABLE = "mm_sys_tenant.contact_email";

    public static final String CONTACT_PHONE_OF_TABLE = "mm_sys_tenant.contact_phone";

    public static final String CONTACT_PERSON_OF_TABLE = "mm_sys_tenant.contact_person";

    public static final String DOMAIN_BIND_OF_TABLE = "mm_sys_tenant.domain_bind";

    public static final String LIFESPAN_OF_TABLE = "mm_sys_tenant.lifespan";

    public static final String REMARKS_OF_TABLE = "mm_sys_tenant.remarks";

    public static final String C_TIME_OF_TABLE = "mm_sys_tenant.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_tenant.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_tenant.id",
            "mm_sys_tenant.tenant_name",
            "mm_sys_tenant.tenant_state",
            "mm_sys_tenant.tenant_type",
            "mm_sys_tenant.contact_email",
            "mm_sys_tenant.contact_phone",
            "mm_sys_tenant.contact_person",
            "mm_sys_tenant.domain_bind",
            "mm_sys_tenant.lifespan",
            "mm_sys_tenant.remarks",
            "mm_sys_tenant.c_time",
            "mm_sys_tenant.m_time"
    };


}
