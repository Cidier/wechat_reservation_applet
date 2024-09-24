package com.yj.reservation.controller.cms;

import com.yj.reservation.business.SysRoleBusiness;
import com.yj.reservation.pojo.cms.dto.MmSysPermissionDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysRoleQuery;
import com.yj.reservation.pojo.cms.dto.MmSysRoleDTO;
import com.yj.reservation.pojo.cms.vo.MmSysRoleVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysRoleService;

import java.util.List;
/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "角色表 API", tags = "角色表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-role")
public class MmSysRoleController {

    private final MmSysRoleService service;
    private final SysRoleBusiness business;

    @ApiOperation("新增 角色表")
    @PostMapping
    public JsonResult addMmSysRole(@Valid @RequestBody MmSysRoleDTO dto) {
        service.addMmSysRole(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 角色表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysRole(@PathVariable Long id, @Valid @RequestBody MmSysRoleDTO dto) {
        dto.setId(id);
        service.modifyMmSysRole(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 角色表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysRole(@PathVariable Long id) {
        service.deleteMmSysRole(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 角色表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysRoleById(@PathVariable Long id) {
        MmSysRoleVO vo = service.queryMmSysRoleById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 角色表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysRoleQuery query) {
        Page<MmSysRoleVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }

    /**
     * 查询
     */
    @GetMapping("/pids/{id}")
    public JsonResult list(@PathVariable Long id) {
        return JsonResult.success().put("pids", business.rolePermissionRelationById(id));
    }

    /**
     * 查询
     */
    @GetMapping("/list")
//  @Security(code="system:role:select")
    public JsonResult list() {
        return JsonResult.success().put("roleList", service.list());
    }


    /**
     * 保存
     */
    @PostMapping("/save")
//  @Security(code="system:role:insert")
    public JsonResult save(@RequestBody MmSysRoleDTO dto) {
        business.save(dto);
        return JsonResult.success();
    }

}
