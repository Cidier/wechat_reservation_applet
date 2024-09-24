package com.yj.reservation.pojo.cms.vo;

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
public class MmSysBasicDictVO implements Serializable {

    @ApiModelProperty("主键")
    private Long id;

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

    @ApiModelProperty("创建时间")
    private Date cTime;

    @ApiModelProperty("修改时间")
    private Date mTime;
}

