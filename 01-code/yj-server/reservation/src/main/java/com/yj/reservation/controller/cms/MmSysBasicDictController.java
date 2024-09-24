package com.yj.reservation.controller.cms;

import com.yj.reservation.business.BasicDictBusiness;
import com.yj.reservation.entity.cms.MmSysBasicDict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmSysBasicDictQuery;
import com.yj.reservation.pojo.cms.dto.MmSysBasicDictDTO;
import com.yj.reservation.pojo.cms.vo.MmSysBasicDictVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmSysBasicDictService;

import java.util.List;
/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 */
@Api(value = "字典表 API", tags = "字典表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-sys-basic-dict")
public class MmSysBasicDictController {

    private final MmSysBasicDictService service;
    private final BasicDictBusiness bussines;

    @ApiOperation("新增 字典表")
    @PostMapping
    public JsonResult addMmSysBasicDict(@Valid @RequestBody MmSysBasicDictDTO dto) {
        service.addMmSysBasicDict(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 字典表")
    @PutMapping("/{id}")
    public JsonResult modifyMmSysBasicDict(@PathVariable Long id, @Valid @RequestBody MmSysBasicDictDTO dto) {
        dto.setId(id);
        service.modifyMmSysBasicDict(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 字典表")
    @DeleteMapping("/{id}")
    public JsonResult deleteMmSysBasicDict(@PathVariable Long id) {
        service.deleteMmSysBasicDict(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 字典表 详情")
    @GetMapping("/{id}")
    public JsonResult queryMmSysBasicDictById(@PathVariable Long id) {
        MmSysBasicDictVO vo = service.queryMmSysBasicDictById(id);
        return JsonResult.success().put("vo", vo);
    }




    @ApiOperation("分页查询 字典表")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmSysBasicDictQuery query) {
        Page<MmSysBasicDictVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }


    /**
     * 查询:
     *  parentId如果写入，就根据上级进行分页查询，否则，查询顶级
     */
    @PostMapping("/pageList")
    public JsonResult list(@RequestBody MmSysBasicDictQuery query) {
        return JsonResult.success().put("pageList", bussines.page(query.getPageNo(), query.getPageSize(), query.getParentId()));
    }

    /**
     * 删除
     */
    @PostMapping("/deleteById/{id}")
    public JsonResult delete(@PathVariable Long id) {
        boolean f = bussines.deleteById(id);
        return f ? JsonResult.success().setMsg("删除完成") : JsonResult.fail().setMsg("删除失败");
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public JsonResult save(@RequestBody MmSysBasicDict bean) {
        boolean f = service.saveOrUpdate(bean);

        return f ? JsonResult.success().setMsg("修改完成") : JsonResult.fail().setMsg("修改失败");
    }

    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "parentId", required = false) Long parentId) {
        return JsonResult.success().put("list", bussines.list(parentId));
    }

    @GetMapping("/listByDictKeyBox")
    public JsonResult listByDictKeyBox(String dictKey){
        return JsonResult.success().put("list", bussines.listByDictKeyBox(dictKey));
    }


}
