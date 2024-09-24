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
    * 角色权限关联表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_role_permission_relation")
@ApiModel(value = "MmSysRolePermissionRelation对象", description = "角色权限关联表")
public class MmSysRolePermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long roleId;


    private Long permissionId;


    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";

    public static final String TABLE_NAME = "mm_sys_role_permission_relation";

    public static final String ROLE_ID_OF_TABLE = "mm_sys_role_permission_relation.role_id";

    public static final String PERMISSION_ID_OF_TABLE = "mm_sys_role_permission_relation.permission_id";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_role_permission_relation.role_id",
            "mm_sys_role_permission_relation.permission_id"
    };


}
