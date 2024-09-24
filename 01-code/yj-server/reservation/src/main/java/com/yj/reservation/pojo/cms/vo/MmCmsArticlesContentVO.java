package com.yj.reservation.pojo.cms.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

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
public class MmCmsArticlesContentVO implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("文章ID")
    private Long articlesId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("修改时间")
    private Date mTime;

    public static final String ID = "id";

    public static final String ARTICLES_ID = "articles_id";

    public static final String CONTENT = "content";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

