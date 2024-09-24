package com.yj.reservation.controller.cms;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmCmsArticlesContentQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsArticlesContentDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsArticlesContentVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmCmsArticlesContentService;

import java.util.List;
/**
 * <p>
 * CMS文章内容 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 */
@Api(value = "CMS文章内容 API", tags = "CMS文章内容")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mm-cms-articles-content")
public class MmCmsArticlesContentController {

    private final MmCmsArticlesContentService service;

    @ApiOperation("新增 CMS文章内容")
    @PostMapping("/save")
    public JsonResult addMmCmsArticlesContent(@Valid @RequestBody MmCmsArticlesContentDTO dto) {
        System.out.println(dto.getContent());
        service.addMmCmsArticlesContent(dto);
        return JsonResult.success();
    }

//    @ApiOperation("修改 CMS文章内容")
//    @PostMapping("/update")
//    public JsonResult modifyMmCmsArticlesContent(@RequestParam Long id, @Valid @RequestBody MmCmsArticlesContentDTO dto) {
//        dto.setId(id);
//        service.modifyMmCmsArticlesContent(dto);
//        return JsonResult.success();
//    }

//    @ApiOperation("删除 CMS文章内容")
//    @GetMapping("/delete")
//    public JsonResult deleteMmCmsArticlesContent(@RequestParam Long id) {
//        service.deleteMmCmsArticlesContent(id);
//        return JsonResult.success();
//    }
//

    @ApiOperation("根据id获取 CMS文章内容 详情")
    @GetMapping("/findById")
    public JsonResult queryMmCmsArticlesContentById(@RequestParam Long articlesId) {
        MmCmsArticlesContentVO vo = service.queryMmCmsArticlesContentById(articlesId);
        return JsonResult.success().put("vo", vo);
    }

    @ApiOperation("删除 用户表")
    @GetMapping("/delete")
    public JsonResult deleteMmSysUser(@RequestParam Long articlesId) {
        service.deleteMmCmsArticlesContent(articlesId);
        return JsonResult.success();
    }
//    @ApiOperation("删除 CMS文章")
//    @GetMapping("/delete")
//    public JsonResult deleteMmCmsArticles(@RequestParam Long id) {
//        System.out.println("\n\n\n\n\n\n\ndelete。。。"+id);
//        service.deleteMmCmsArticles(id);
//        return JsonResult.success();
//    }

    @ApiOperation("分页查询 CMS文章内容")
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody MmCmsArticlesContentQuery query) {
        Page<MmCmsArticlesContentVO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
