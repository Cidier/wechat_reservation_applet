package com.yj.reservation.pojo.cms.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* <p>
    * 用户表
    * </p>
*
* @author yang
* @since 2024-03-11
*/
@Getter
@Setter
@Accessors(chain = true)
public class MmSysUserVO implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("用户状态")
    private String state;

    @ApiModelProperty("用户类型")
    private String type;

    @ApiModelProperty("用户登录名称")
    private String userName;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("描述")
    private String remarks;

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("修改时间")
    private Date mTime;

    private Long[] roleIds;



    public static final String ID = "id";

    public static final String TENANT_ID = "tenant_id";

    public static final String STATE = "state";

    public static final String TYPE = "type";

    public static final String USER_NAME = "user_name";

    public static final String NAME = "name";

    public static final String USER_PASSWORD = "user_password";

    public static final String SALT = "salt";

    public static final String EMAIL = "email";

    public static final String PHONE = "phone";

    public static final String NICK_NAME = "nick_name";

    public static final String AVATAR = "avatar";

    public static final String REMARKS = "remarks";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

