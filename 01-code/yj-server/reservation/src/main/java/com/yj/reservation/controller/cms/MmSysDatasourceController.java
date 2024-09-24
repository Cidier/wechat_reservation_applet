package com.yj.reservation.controller.cms;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysDatasourceQuery;
import com.yj.reservation.pojo.cms.dto.MmSysDatasourceDTO;
import com.yj.reservation.pojo.cms.vo.MmSysDatasourceVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysDatasourceService;

import java.util.List;
/**
 * <p>
 * 数据源配置表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "数据源配置表 API", tags = "数据源配置表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-datasource")
public class MmSysDatasourceController {

    private final MmSysDatasourceService service;

    @ApiOperation("新增 数据源配置表")
    @PostMapping
    public JsonResult addMmSysDatasource(@Valid @RequestBody MmSysDatasourceDTO dto) {
        service.addMmSysDatasource(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 数据源配置表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysDatasource(@PathVariable Long id, @Valid @RequestBody MmSysDatasourceDTO dto) {
        dto.setId(id);
        service.modifyMmSysDatasource(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 数据源配置表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysDatasource(@PathVariable Long id) {
        service.deleteMmSysDatasource(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 数据源配置表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysDatasourceById(@PathVariable Long id) {
        MmSysDatasourceVO vo = service.queryMmSysDatasourceById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 数据源配置表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysDatasourceQuery query) {
        Page<MmSysDatasourceVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
