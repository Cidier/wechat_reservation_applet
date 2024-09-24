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
    * CMS文章
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_cms_articles")
@ApiModel(value = "MmCmsArticles对象", description = "CMS文章")
public class MmCmsArticles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long menuId;

    private String coverImg;

    private String title;
//排序 升序降序
    private Integer sortOrder;
//状态
    private String state;
//类型
    private String type;
//属性
    private String attrData;

    private String remark;

    @TableField(value = "c_time", fill = FieldFill.INSERT)
    private Date cTime;

    @TableField(update = "NOW()")
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

    public static final String TABLE_NAME = "mm_cms_articles";

    public static final String ID_OF_TABLE = "mm_cms_articles.id";

    public static final String TITLE_OF_TABLE = "mm_cms_articles.title";

    public static final String SORT_ORDER_OF_TABLE = "mm_cms_articles.sort_order";

    public static final String STATE_OF_TABLE = "mm_cms_articles.state";

    public static final String TYPE_OF_TABLE = "mm_cms_articles.type";

    public static final String ATTR_DATA_OF_TABLE = "mm_cms_articles.attr_data";

    public static final String REMARK_OF_TABLE = "mm_cms_articles.remark";

    public static final String C_TIME_OF_TABLE = "mm_cms_articles.c_time";

    public static final String M_TIME_OF_TABLE = "mm_cms_articles.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_cms_articles.id",
            "mm_cms_articles.menu_id",
            "mm_cms_articles.cover_img",
            "mm_cms_articles.title",
            "mm_cms_articles.sort_order",
            "mm_cms_articles.state",
            "mm_cms_articles.type",
            "mm_cms_articles.attr_data",
            "mm_cms_articles.remark",
            "mm_cms_articles.c_time",
            "mm_cms_articles.m_time"
    };


}
