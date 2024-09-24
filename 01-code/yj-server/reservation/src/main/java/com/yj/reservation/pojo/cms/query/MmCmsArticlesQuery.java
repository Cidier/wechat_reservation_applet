package com.yj.reservation.pojo.cms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.yj.reservation.common.bean.BasePageQuery;

/**
 * <p>
 * CMS文章
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 */
@Getter
@Setter
@Accessors(chain = true)
@Data
@ApiModel(value = "MmCmsArticles 分页参数", description = "CMS文章")
public class MmCmsArticlesQuery extends BasePageQuery{

private String id;

private String menuId;

private String title;


}
