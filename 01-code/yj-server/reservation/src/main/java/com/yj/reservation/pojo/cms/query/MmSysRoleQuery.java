package com.yj.reservation.pojo.cms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.yj.reservation.common.bean.BasePageQuery;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "MmSysRole 分页参数", description = "角色表")
public class MmSysRoleQuery extends BasePageQuery{



}
