package com.yj.reservation.pojo.cms.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Getter
@Setter
@Accessors(chain = true)
public class MmSysBasicDictDTO implements Serializable {

    private Long id;//走这里

    @ApiModelProperty("父主键")
    private Integer parentId;

    @ApiModelProperty("状态：1启用，0禁止")
    private Integer state;

    @ApiModelProperty("字典码")
    private String code;

    @ApiModelProperty("字典值")
    private String dictKey;

    @ApiModelProperty("字典名称")
    private String dictValue;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("字典备注")
    private String remark;

    private Date cTime;//走这里

    @ApiModelProperty("修改时间")
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
}

