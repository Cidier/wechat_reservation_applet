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
    * 权限表
    * </p>
*
* @author yang
* @since 2024-03-11
*/
@Getter
@Setter
@Accessors(chain = true)
public class MmSysPermissionVO implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限类型")
    private String type;

    @ApiModelProperty("排序")
    private Integer od;

    @ApiModelProperty("上级权限ID 顶级为空")
    private Long parentId;

    @ApiModelProperty("请求地址URL")
    private String url;

    @ApiModelProperty("路由地址")
    private String routeUrl;

    @ApiModelProperty("权限状态")
    private Integer state;

    @ApiModelProperty("权限icon图片")
    private String icon;

    @ApiModelProperty("上级权限id地址串")
    private String parentIds;

    @ApiModelProperty("权限代码")
    private String precode;

    private List<MmSysPermissionVO> childrens;
}

