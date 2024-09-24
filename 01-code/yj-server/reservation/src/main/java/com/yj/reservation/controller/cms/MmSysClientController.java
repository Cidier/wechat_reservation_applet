package com.yj.reservation.controller.cms;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysClientQuery;
import com.yj.reservation.pojo.cms.dto.MmSysClientDTO;
import com.yj.reservation.pojo.cms.vo.MmSysClientVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysClientService;

import java.util.List;
/**
 * <p>
 * 客户端表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "客户端表 API", tags = "客户端表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-client")
public class MmSysClientController {

    private final MmSysClientService service;

    @ApiOperation("新增 客户端表")
    @PostMapping
    public JsonResult addMmSysClient(@Valid @RequestBody MmSysClientDTO dto) {
        service.addMmSysClient(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 客户端表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysClient(@PathVariable Long id, @Valid @RequestBody MmSysClientDTO dto) {
        dto.setId(id);
        service.modifyMmSysClient(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 客户端表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysClient(@PathVariable Long id) {
        service.deleteMmSysClient(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 客户端表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysClientById(@PathVariable Long id) {
        MmSysClientVO vo = service.queryMmSysClientById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 客户端表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysClientQuery query) {
        Page<MmSysClientVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
