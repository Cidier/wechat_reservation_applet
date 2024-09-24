package com.yj.reservation.pojo.cms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.yj.reservation.common.bean.BasePageQuery;

/**
 * <p>
 * CMS文章内容
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "MmCmsArticlesContent 分页参数", description = "CMS文章内容")
public class MmCmsArticlesContentQuery extends BasePageQuery{



}
