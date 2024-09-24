package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import ${paramQueryPath}.${entity}Query;
import ${dtoPath}.${entity}DTO;
import ${voPath}.${entity}VO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 *
 */
@Slf4j
@Service
@AllArgsConstructor
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if table.serviceInterface>, ${table.serviceName}</#if> {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}><#if table.serviceInterface>
        implements ${table.serviceName}</#if> {

    /**
    * 新增 ${table.comment!}
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add${entity}(${entity}DTO dto){
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 ${table.comment!}
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify${entity}(${entity}DTO dto){
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 ${table.comment!}
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete${entity}(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 ${table.comment!} 详情
    *
    * @param id 主键
    */
    @Override
    public ${entity}VO query${entity}ById(Long id){
        ${entity} entity = super.getById(id);
        ${entity}VO vo = new ${entity}VO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 ${table.comment!}
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<${entity}VO> pageList(${entity}Query query) {
        Page<${entity}> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<${entity}>());
        List<${entity}> list = page.getRecords();
        List<${entity}VO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            ${entity}VO vo = new ${entity}VO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<${entity}VO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
</#if>
