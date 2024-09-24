package com.yj.reservation.service.cms.impl;

import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.entity.cms.MmCmsArticles;
import com.yj.reservation.mapper.cms.MmCmsArticlesMapper;
import com.yj.reservation.service.cms.MmCmsArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmCmsArticlesQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsArticlesDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsArticlesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * CMS文章 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmCmsArticlesServiceImpl extends ServiceImpl<MmCmsArticlesMapper, MmCmsArticles>
        implements MmCmsArticlesService {

    /**
    * 新增 CMS文章
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addMmCmsArticles(MmCmsArticlesDTO dto){
        MmCmsArticles entity = new MmCmsArticles();
        BeanUtils.copyProperties(dto, entity);
        Long id = dto.getId();
        if(dto.getId() == null){
            super.save(entity);
            id = entity.getId();
        }
        else{
            super.updateById(entity);
        }


        return id;
    }

    /**
    * 修改 CMS文章
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmCmsArticles(MmCmsArticlesDTO dto){
        MmCmsArticles entity = new MmCmsArticles();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 CMS文章
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmCmsArticles(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 CMS文章 详情
    *
    * @param id 主键
    */
    @Override
    public MmCmsArticlesVO queryMmCmsArticlesById(Long id){
        MmCmsArticles entity = super.getById(id);
        MmCmsArticlesVO vo = new MmCmsArticlesVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 CMS文章
    *
    * @param query 参数
    * @return
    */
    @Override
   public Page<MmCmsArticlesVO> pageList(MmCmsArticlesQuery query) {
//        Page<MmCmsArticles> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
//                new LambdaQueryWrapper<MmCmsArticles>());
//        List<MmCmsArticles> list = page.getRecords();
//        List<MmCmsArticlesVO> resultList = new ArrayList<>();
//        list.stream().forEach(bean -> {
//            MmCmsArticlesVO vo = new MmCmsArticlesVO();
//            BeanUtils.copyProperties(bean, vo);
//            resultList.add(vo);
//        });
//        return new Page<MmCmsArticlesVO>().setTotal(page.getTotal()).setRecords(resultList);
//    }

        LambdaQueryWrapper<MmCmsArticles> queryWrapper =  new LambdaQueryWrapper<MmCmsArticles>();
        if(query.getMenuId()!=null){
//            MmCmsArticles::getMenuId：这是一个方法引用，指向MmCmsArticles类中的getMenuId方法。
//            MyBatis Plus会解析这个方法引用，自动将其映射到对应的数据库字段名，即menu_id
            queryWrapper.eq(MmCmsArticles::getMenuId, query.getMenuId());
        }

//        System.out.println("Returned Page Current: " + query.getCurrent());
        System.out.println("queryCurrent"+query.getPageNo());
        Page<MmCmsArticles> page = page(new Page<>(query.getPageNo(), query.getPageSize()),queryWrapper);
        List<MmCmsArticles> list = page.getRecords();
        System.out.println("list"+list+"\npagesCurrent"+page.getCurrent());
        List<MmCmsArticlesVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmCmsArticlesVO vo = new MmCmsArticlesVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmCmsArticlesVO>().setTotal(page.getTotal()).setRecords(resultList).setCurrent(page.getCurrent());
    }

}
