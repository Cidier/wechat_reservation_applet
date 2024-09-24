package com.yj.reservation.pojo.cms.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* <p>
    * 租户表
    * </p>
*
* @author yang
* @since 2024-03-11
*/
@Getter
@Setter
@Accessors(chain = true)
public class MmSysTenantVO implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("用户名称")
    private String tenantName;

    @ApiModelProperty("租户状态")
    private String tenantState;

    @ApiModelProperty("租户类型")
    private String tenantType;

    @ApiModelProperty("联系人邮箱")
    private String contactEmail;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

    @ApiModelProperty("联系人姓名")
    private String contactPerson;

    @ApiModelProperty("绑定域名")
    private String domainBind;

    @ApiModelProperty("有效期")
    private Date lifespan;

    @ApiModelProperty("描述")
    private String remarks;

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("修改时间")
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
}

