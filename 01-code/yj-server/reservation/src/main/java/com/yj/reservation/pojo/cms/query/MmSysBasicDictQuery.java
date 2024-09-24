package com.yj.reservation.pojo.cms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.yj.reservation.common.bean.BasePageQuery;
import org.springframework.web.bind.annotation.RequestParam;

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
@ApiModel(value = "MmSysBasicDict 分页参数", description = "字典表")
public class MmSysBasicDictQuery extends BasePageQuery{

    private Long parentId;

}
