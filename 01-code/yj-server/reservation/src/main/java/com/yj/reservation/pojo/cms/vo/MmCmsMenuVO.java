package com.yj.reservation.pojo.cms.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
* <p>
    * CMS菜单配置
    * </p>
*
* @author yang
* @since 2024-01-18
*/
@Getter
@Setter
@Accessors(chain = true)
public class MmCmsMenuVO implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("菜单名称")
    private String menuTitle;

    @ApiModelProperty("上级菜单ID")
    private Long parentId;

    @ApiModelProperty("菜单类型")
    private String type;

    @ApiModelProperty("排序")
    private Integer sortOrder;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("修改时间")
    private Date mTime;

    private List<MmCmsMenuVO> childrens;

    public static final String ID = "id";

    public static final String MENU_TITLE = "menu_title";

    public static final String PARENT_ID = "parent_id";

    public static final String TYPE = "type";

    public static final String SORT_ORDER = "sort_order";

    public static final String STATE = "state";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";
}

