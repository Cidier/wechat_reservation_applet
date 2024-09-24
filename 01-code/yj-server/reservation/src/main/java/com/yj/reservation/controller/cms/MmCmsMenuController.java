package com.yj.reservation.controller.cms;

import com.yj.reservation.business.MenuBusiness;
import com.yj.reservation.pojo.cms.dto.MmSysPermissionDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmCmsMenuQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsMenuDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsMenuVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmCmsMenuService;

import java.util.List;
/**
 * <p>
 * CMS菜单配置 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 */

@Api(value = "CMS菜单配置 API", tags = "CMS菜单配置")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-cms-menu")
public class MmCmsMenuController {

    private final MmCmsMenuService service;
    private final MenuBusiness menuBusiness;
    @ApiOperation("新增 CMS菜单配置")
    @PostMapping("/save")
    public JsonResult addMmCmsMenu(@Valid @RequestBody MmCmsMenuDTO dto) {
        service.addMmCmsMenu(dto);
        return JsonResult.success();
    }

    @ApiOperation("修改 CMS菜单配置")
    @PutMapping("/{id}")
    public JsonResult modifyMmCmsMenu(@PathVariable Long id, @Valid @RequestBody MmCmsMenuDTO dto) {
        dto.setId(id);
        service.modifyMmCmsMenu(dto);
        return JsonResult.success();
    }

    @ApiOperation("删除 CMS菜单配置")
    @DeleteMapping("/delete/{id}")
    public JsonResult deleteMmCmsMenu(@PathVariable Long id) {
        service.deleteMmCmsMenu(id);
        return JsonResult.success();
    }


    @ApiOperation("根据id获取 CMS菜单配置 详情")
    @GetMapping("/{id}")
    public JsonResult   queryMmCmsMenuById(@PathVariable Long id) {
        MmCmsMenuVO vo = service.queryMmCmsMenuById(id);
        return JsonResult.success().put("vo", vo);
    }


    @ApiOperation("分页查询 CMS菜单配置")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmCmsMenuQuery query) {
        Page<MmCmsMenuVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }

    @GetMapping("/menuTree")
    public JsonResult list() {
        return JsonResult.success().put("menu", menuBusiness.menuTree());
    }

    /**
     * 查询
     */
    @GetMapping("/treeList")
//  @Security(code = "system:permission:select")
    public JsonResult pageList() {

        return JsonResult.success().put("menu", menuBusiness.buildTreeGrid());
    }

}
