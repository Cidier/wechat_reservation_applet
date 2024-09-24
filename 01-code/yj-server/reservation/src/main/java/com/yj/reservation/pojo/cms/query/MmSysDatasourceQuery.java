package com.yj.reservation.pojo.cms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.yj.reservation.common.bean.BasePageQuery;

/**
 * <p>
 * 数据源配置表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "MmSysDatasource 分页参数", description = "数据源配置表")
public class MmSysDatasourceQuery extends BasePageQuery{



}
