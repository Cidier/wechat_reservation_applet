package com.yj.reservation.controller.cms;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysTenantQuery;
import com.yj.reservation.pojo.cms.dto.MmSysTenantDTO;
import com.yj.reservation.pojo.cms.vo.MmSysTenantVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysTenantService;

import java.util.List;
/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "租户表 API", tags = "租户表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-tenant")
public class MmSysTenantController {

    private final MmSysTenantService service;

    @ApiOperation("新增 租户表")
    @PostMapping
    public JsonResult addMmSysTenant(@Valid @RequestBody MmSysTenantDTO dto) {
        service.addMmSysTenant(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 租户表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysTenant(@PathVariable Long id, @Valid @RequestBody MmSysTenantDTO dto) {
        dto.setId(id);
        service.modifyMmSysTenant(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 租户表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysTenant(@PathVariable Long id) {
        service.deleteMmSysTenant(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 租户表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysTenantById(@PathVariable Long id) {
        MmSysTenantVO vo = service.queryMmSysTenantById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 租户表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysTenantQuery query) {
        Page<MmSysTenantVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
