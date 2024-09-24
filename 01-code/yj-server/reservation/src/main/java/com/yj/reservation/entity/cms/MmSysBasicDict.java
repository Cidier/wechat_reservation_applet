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
    * 字典表
    * </p>
 *
 * @author yang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mm_sys_basic_dict")
@ApiModel(value = "MmSysBasicDict对象", description = "字典表")
public class MmSysBasicDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Long id;


    private Integer parentId;


    private Integer state;


    private String code;


    private String dictKey;


    private String dictValue;


    private Integer sort;


    private String remark;


    private Date cTime;


    private Date mTime;


    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String STATE = "state";

    public static final String CODE = "code";

    public static final String DICT_KEY = "dict_key";

    public static final String DICT_VALUE = "dict_value";

    public static final String SORT = "sort";

    public static final String REMARK = "remark";

    public static final String C_TIME = "c_time";

    public static final String M_TIME = "m_time";

    public static final String TABLE_NAME = "mm_sys_basic_dict";

    public static final String ID_OF_TABLE = "mm_sys_basic_dict.id";

    public static final String PARENT_ID_OF_TABLE = "mm_sys_basic_dict.parent_id";

    public static final String STATE_OF_TABLE = "mm_sys_basic_dict.state";

    public static final String CODE_OF_TABLE = "mm_sys_basic_dict.code";

    public static final String DICT_KEY_OF_TABLE = "mm_sys_basic_dict.dict_key";

    public static final String DICT_VALUE_OF_TABLE = "mm_sys_basic_dict.dict_value";

    public static final String SORT_OF_TABLE = "mm_sys_basic_dict.sort";

    public static final String REMARK_OF_TABLE = "mm_sys_basic_dict.remark";

    public static final String C_TIME_OF_TABLE = "mm_sys_basic_dict.c_time";

    public static final String M_TIME_OF_TABLE = "mm_sys_basic_dict.m_time";

    public static final String[] ALL_COLUMNS = new String[]{
        "mm_sys_basic_dict.id",
            "mm_sys_basic_dict.parent_id",
            "mm_sys_basic_dict.state",
            "mm_sys_basic_dict.code",
            "mm_sys_basic_dict.dict_key",
            "mm_sys_basic_dict.dict_value",
            "mm_sys_basic_dict.sort",
            "mm_sys_basic_dict.remark",
            "mm_sys_basic_dict.c_time",
            "mm_sys_basic_dict.m_time"
    };


}
