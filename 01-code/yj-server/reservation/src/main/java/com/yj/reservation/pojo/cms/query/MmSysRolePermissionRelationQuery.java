package com.yj.reservation.pojo.cms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.yj.reservation.common.bean.BasePageQuery;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "MmSysRolePermissionRelation 分页参数", description = "角色权限关联表")
public class MmSysRolePermissionRelationQuery extends BasePageQuery{



}
