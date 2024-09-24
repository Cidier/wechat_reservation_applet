package com.yj.reservation.controller.cms;

import com.yj.reservation.business.SysPermissionBusiness;
import com.yj.reservation.entity.cms.MmSysPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysPermissionQuery;
import com.yj.reservation.pojo.cms.dto.MmSysPermissionDTO;
import com.yj.reservation.pojo.cms.vo.MmSysPermissionVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysPermissionService;

import java.util.List;
/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "权限表 API", tags = "权限表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-permission")
public class MmSysPermissionController {

    private final MmSysPermissionService service;
    private final SysPermissionBusiness business;

    @ApiOperation("新增 权限表")
    @PostMapping
    public JsonResult addMmSysPermission(@Valid @RequestBody MmSysPermissionDTO dto) {
        service.addMmSysPermission(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 权限表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysPermission(@PathVariable Long id, @Valid @RequestBody MmSysPermissionDTO dto) {
        dto.setId(id);
        service.modifyMmSysPermission(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 权限表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysPermission(@PathVariable Long id) {
        service.deleteMmSysPermission(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 权限表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysPermissionById(@PathVariable Long id) {
        MmSysPermissionVO vo = service.queryMmSysPermissionById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 权限表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysPermissionQuery query) {
        Page<MmSysPermissionVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }



    /**
     * 查询type=menu的list
     */
    @GetMapping("/menuTree")
    public JsonResult list() {
        return JsonResult.success().put("permission", business.menuTree());
    }

    /**
     * 查询
     */
    @GetMapping("/treeList")
//  @Security(code = "system:permission:select")
    public JsonResult pageList() {
        return JsonResult.success().put("permission", business.buildTreeGrid());
    }

    /**
     * 保存
     */
    @PostMapping("/save")
//  @Security(code="system:permission:insert")
    public JsonResult save(@RequestBody MmSysPermissionDTO dto) {
        if (dto.getId() == null) {
            service.addMmSysPermission(dto);
        } else {
            service.modifyMmSysPermission(dto);
        }
        return JsonResult.success();
    }

}
