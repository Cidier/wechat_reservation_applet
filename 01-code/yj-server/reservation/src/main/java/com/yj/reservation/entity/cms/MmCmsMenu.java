package com.yj.reservation.entity.cms;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * CMS菜单配置
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_cms_menu")
@ApiModel(value = "MmCmsMenu对象", description = "CMS菜单配置")
public class MmCmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String menuTitle;

    private Long parentId;

    private String type;

    private Integer sortOrder;

    private String state;

    private String remark;

    @TableField(value = "c_time", fill = FieldFill.INSERT)
    private Date cTime;

//    @TableField(value = "m_time",update = "NOW()")
    private Date mTime;


    public static final String ID = "id";

    public static final String MENU_TITLE = "menu_title";

    public static final String PARENT_ID = "parent_id";

    public static final String TYPE = "type";

    public static final String SORT_ORDER = "sort_order";

    public static final String STATE = "state";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_cms_menu";

    public static final String ID_OF_TABLE = "mm_cms_menu.id";

    public static final String MENU_TITLE_OF_TABLE = "mm_cms_menu.menu_title";

    public static final String PARENT_ID_OF_TABLE = "mm_cms_menu.parent_id";

    public static final String TYPE_OF_TABLE = "mm_cms_menu.type";

    public static final String SORT_ORDER_OF_TABLE = "mm_cms_menu.sort_order";

    public static final String STATE_OF_TABLE = "mm_cms_menu.state";

    public static final String REMARK_OF_TABLE = "mm_cms_menu.remark";

    public static final String C_TIME_OF_TABLE = "mm_cms_menu.c_time";

    public static final String M_TIME_OF_TABLE = "mm_cms_menu.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_cms_menu.id",
            "mm_cms_menu.menu_title",
            "mm_cms_menu.parent_id",
            "mm_cms_menu.type",
            "mm_cms_menu.sort_order",
            "mm_cms_menu.state",
            "mm_cms_menu.remark",
            "mm_cms_menu.c_time",
            "mm_cms_menu.m_time"
    };


}
