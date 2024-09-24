package ${package.Controller};

import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid; // javax.validation.Valid 是SpringBoot2.7的； jakarta.validation.Valid 是 SpringBoot3
import ${paramQueryPath}.${entity}Query;
import ${dtoPath}.${entity}DTO;
import ${voPath}.${entity}VO;
import ${package.Parent}.common.bean.JsonResult;
import ${package.Service}.${table.serviceName};

import java.util.List;
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger>
@Api(value = "${table.comment!} API", tags = "${table.comment!}")
</#if>
@RequiredArgsConstructor
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} service;

<#if swagger>
    @ApiOperation("新增 ${table.comment!}")
<#else>
    /**
     * 新增 ${table.comment!}
     *
     * @param dto 参数
     */
</#if>
    @PostMapping
    public JsonResult add${entity}(@Valid @RequestBody ${entity}DTO dto) {
        service.add${entity}(dto);
        return JsonResult.success();
    }

    <#if table.controllerName?contains("Relation")>
    //双向关联关系类，没有ID

    <#else>
    <#if swagger>
    @ApiOperation("修改 ${table.comment!}")
    <#else>
    /**
    * 修改 ${table.comment!}
    *
    * @param id 主键
    * @param dto 参数
    */
    </#if>
    @PutMapping("/{id}")
    public JsonResult modify${entity}(@PathVariable Long id, @Valid @RequestBody ${entity}DTO dto) {
        dto.setId(id);
        service.modify${entity}(dto);
        return JsonResult.success();
    }

    <#if swagger>
    @ApiOperation("删除 ${table.comment!}")
    <#else>
    /**
    * 删除 ${table.comment!}
    *
    * @param id 主键
    */
    </#if>
    @DeleteMapping("/{id}")
    public JsonResult delete${entity}(@PathVariable Long id) {
        service.delete${entity}(id);
        return JsonResult.success();
    }


    <#if swagger>
    @ApiOperation("根据id获取 ${table.comment!} 详情")
    <#else>
    /**
    * 根据id获取 ${table.comment!} 详情
    *
    * @param id 主键
    */
    </#if>
    @GetMapping("/{id}")
    public JsonResult query${entity}ById(@PathVariable Long id) {
        ${entity}VO vo = service.query${entity}ById(id);
        return JsonResult.success().put("vo", vo);
    }
    </#if>




    <#if swagger>
    @ApiOperation("分页查询 ${table.comment!}")
    <#else>
    /**
     * 分页查询 ${table.comment!}
     *
     * @param dto 参数
     * @return
     */
    </#if>
    @PostMapping("/page")
    public JsonResult pagingList(@Valid @RequestBody ${entity}Query query) {
        Page<${entity}VO> page = service.pageList(query);
        return JsonResult.success().put("page", page);
    }
}
</#if>
