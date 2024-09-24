package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

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
public class MmCmsArticlesDTO implements Serializable {

    private Long id;

    private Long menuId;

    private String coverImg;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("排序")
    private Integer sortOrder;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("属性信息")
    private String attrData;

    @ApiModelProperty("备注")
    private String remark;

    private Date cTime;

    @ApiModelProperty("修改时间")
    private Date mTime;

    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String COVER_IMG = "cover_img";

    public static final String TITLE = "title";

    public static final String SORT_ORDER = "sort_order";

    public static final String STATE = "state";

    public static final String TYPE = "type";

    public static final String ATTR_DATA = "attr_data";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

