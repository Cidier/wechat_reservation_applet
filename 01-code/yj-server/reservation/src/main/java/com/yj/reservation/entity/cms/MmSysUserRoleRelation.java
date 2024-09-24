package com.yj.reservation.entity.cms;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 角色用户关联表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_user_role_relation")
@ApiModel(value = "MmSysUserRoleRelation对象", description = "角色用户关联表")
public class MmSysUserRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long userId;


    private Long roleId;


    public static final String USER_ID = "user_id";

    public static final String ROLE_ID = "role_id";

    public static final String TABLE_NAME = "mm_sys_user_role_relation";

    public static final String USER_ID_OF_TABLE = "mm_sys_user_role_relation.user_id";

    public static final String ROLE_ID_OF_TABLE = "mm_sys_user_role_relation.role_id";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_user_role_relation.user_id",
            "mm_sys_user_role_relation.role_id"
    };


}
