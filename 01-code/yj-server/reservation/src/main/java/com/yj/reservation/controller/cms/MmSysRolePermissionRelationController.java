package com.yj.reservation.controller.cms;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysRolePermissionRelationQuery;
import com.yj.reservation.pojo.cms.dto.MmSysRolePermissionRelationDTO;
import com.yj.reservation.pojo.cms.vo.MmSysRolePermissionRelationVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysRolePermissionRelationService;

import java.util.List;
/**
 * <p>
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "角色权限关联表 API", tags = "角色权限关联表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-role-permission-relation")
public class MmSysRolePermissionRelationController {

    private final MmSysRolePermissionRelationService service;

    @ApiOperation("新增 角色权限关联表")
    @PostMapping
    public JsonResult addMmSysRolePermissionRelation(@Valid @RequestBody MmSysRolePermissionRelationDTO dto) {
        service.addMmSysRolePermissionRelation(dto);
        return JsonResult.success();
    }

    //双向关联关系类，没有ID





    @ApiOperation("分页查询 角色权限关联表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysRolePermissionRelationQuery query) {
        Page<MmSysRolePermissionRelationVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
