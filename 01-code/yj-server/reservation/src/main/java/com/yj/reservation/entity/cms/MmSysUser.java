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
    * 用户表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_user")
@ApiModel(value = "MmSysUser对象", description = "用户表")
public class MmSysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Long id;


    private String tenantId;


    private String state;


    private String type;


    private String userName;


    private String name;


    private String userPassword;


    private String salt;


    private String email;


    private String phone;


    private String nickName;


    private String avatar;


    private String remarks;


    private Date cTime;


    private Date mTime;


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

    public static final String TABLE_NAME = "mm_sys_user";

    public static final String ID_OF_TABLE = "mm_sys_user.id";

    public static final String TENANT_ID_OF_TABLE = "mm_sys_user.tenant_id";

    public static final String STATE_OF_TABLE = "mm_sys_user.state";

    public static final String TYPE_OF_TABLE = "mm_sys_user.type";

    public static final String USER_NAME_OF_TABLE = "mm_sys_user.user_name";

    public static final String NAME_OF_TABLE = "mm_sys_user.name";

    public static final String USER_PASSWORD_OF_TABLE = "mm_sys_user.user_password";

    public static final String SALT_OF_TABLE = "mm_sys_user.salt";

    public static final String EMAIL_OF_TABLE = "mm_sys_user.email";

    public static final String PHONE_OF_TABLE = "mm_sys_user.phone";

    public static final String NICK_NAME_OF_TABLE = "mm_sys_user.nick_name";

    public static final String AVATAR_OF_TABLE = "mm_sys_user.avatar";

    public static final String REMARKS_OF_TABLE = "mm_sys_user.remarks";

    public static final String C_TIME_OF_TABLE = "mm_sys_user.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_user.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_user.id",
            "mm_sys_user.tenant_id",
            "mm_sys_user.state",
            "mm_sys_user.type",
            "mm_sys_user.user_name",
            "mm_sys_user.name",
            "mm_sys_user.user_password",
            "mm_sys_user.salt",
            "mm_sys_user.email",
            "mm_sys_user.phone",
            "mm_sys_user.nick_name",
            "mm_sys_user.avatar",
            "mm_sys_user.remarks",
            "mm_sys_user.c_time",
            "mm_sys_user.m_time"
    };


}
