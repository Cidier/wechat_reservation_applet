package com.yj.reservation.controller.cms;

import com.yj.reservation.common.security.annotation.OpenApi;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import com.yj.reservation.pojo.cms.query.MmCmsArticlesQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsArticlesDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsArticlesVO;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.service.cms.MmCmsArticlesService;

import java.util.List;
/**
 * <p>
 * CMS文章 前端控制器
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 */
@Api(value = "CMS文章 API", tags = "CMS文章")
@RequiredArgsConstructor
@RestController
///mm-cms-articles/pageList
@RequestMapping("/mm-cms-articles")
public class MmCmsArticlesController {

    private final MmCmsArticlesService service;


    @ApiOperation("删除 CMS文章")
    @GetMapping("/delete")
    public JsonResult deleteMmCmsArticles(@RequestParam Long id) {
        System.out.println("\n\n\n\n\n\n\ndelete。。。"+id);
        service.deleteMmCmsArticles(id);
        return JsonResult.success();
    }

    @ApiOperation("新增 CMS文章")
    @PostMapping("/save")
    public JsonResult addMmCmsArticles( @RequestBody MmCmsArticlesDTO dto) {
        System.out.println("新增文章"+dto.getTitle()+"\n\n\n\n\n");
        long articlesId = service.addMmCmsArticles(dto);
        System.out.println("articleId is"+articlesId);
        return JsonResult.success().put("articlesId",articlesId);
    }

    @ApiOperation("修改 CMS文章")
    @PostMapping("/update")
    public JsonResult modifyMmCmsArticles(@RequestParam Long id,  @RequestBody MmCmsArticlesDTO dto) {
        dto.setId(id);
        service.modifyMmCmsArticles(dto);
        return JsonResult.success();
    }




    @ApiOperation("根据id获取 CMS文章 详情")
    @GetMapping("/findById")
    public JsonResult queryMmCmsArticlesById(@RequestParam  Long id) {
        MmCmsArticlesVO vo = service.queryMmCmsArticlesById(id);
        return JsonResult.success().put("vo", vo);
    }


    @ApiOperation("分页查询 CMS文章")
    @PostMapping("/pageList")
    public JsonResult pageList(@Valid @RequestBody MmCmsArticlesQuery query) {
//        listaMmCmsArticlesQuery(id=null, menuId=10, title=null)101
        System.out.println("lista"+query.toString()+query.getMenuId()+"pageNo is"+query.getPageNo()+"pageSize"+query.getPageSize());
        Page<MmCmsArticlesVO> page = service.pageList(query);
        System.out.println("pages is current:"+page.getCurrent());

        return JsonResult.success().put("page", page);
    }

//    @PostMapping("/")
//    public JsonResult list(){
//        service.list();
//    }
}
