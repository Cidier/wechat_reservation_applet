package com.yj.reservation.controller.cms;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysDeptQuery;
import com.yj.reservation.pojo.cms.dto.MmSysDeptDTO;
import com.yj.reservation.pojo.cms.vo.MmSysDeptVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysDeptService;

import java.util.List;
/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "部门表 API", tags = "部门表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-dept")
public class MmSysDeptController {

    private final MmSysDeptService service;

    @ApiOperation("新增 部门表")
    @PostMapping
    public JsonResult addMmSysDept(@Valid @RequestBody MmSysDeptDTO dto) {
        service.addMmSysDept(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 部门表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysDept(@PathVariable Long id, @Valid @RequestBody MmSysDeptDTO dto) {
        dto.setId(id);
        service.modifyMmSysDept(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 部门表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysDept(@PathVariable Long id) {
        service.deleteMmSysDept(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 部门表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysDeptById(@PathVariable Long id) {
        MmSysDeptVO vo = service.queryMmSysDeptById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 部门表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysDeptQuery query) {
        Page<MmSysDeptVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
