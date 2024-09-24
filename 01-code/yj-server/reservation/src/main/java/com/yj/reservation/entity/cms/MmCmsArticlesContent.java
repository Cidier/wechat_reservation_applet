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
    * CMS文章内容
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_cms_articles_content")
@ApiModel(value = "MmCmsArticlesContent对象", description = "CMS文章内容")
public class MmCmsArticlesContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articlesId;

    private String content;

    private String remark;

    @TableField(value = "c_time", fill = FieldFill.INSERT)
    private Date cTime;

    @TableField(update = "NOW()")
    private Date mTime;


    public static final String ID = "id";

    public static final String ARTICLES_ID = "articles_id";

    public static final String CONTENT = "content";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_cms_articles_content";

    public static final String ID_OF_TABLE = "mm_cms_articles_content.id";

    public static final String ARTICLES_ID_OF_TABLE = "mm_cms_articles_content.articles_id";

    public static final String CONTENT_OF_TABLE = "mm_cms_articles_content.content";

    public static final String REMARK_OF_TABLE = "mm_cms_articles_content.remark";

    public static final String C_TIME_OF_TABLE = "mm_cms_articles_content.c_time";

    public static final String M_TIME_OF_TABLE = "mm_cms_articles_content.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_cms_articles_content.id",
            "mm_cms_articles_content.articles_id",
            "mm_cms_articles_content.content",
            "mm_cms_articles_content.remark",
            "mm_cms_articles_content.c_time",
            "mm_cms_articles_content.m_time"
    };


}
