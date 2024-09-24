package com.yj.reservation.service.cms.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmCmsArticlesContent;
import com.yj.reservation.mapper.cms.MmCmsArticlesContentMapper;
import com.yj.reservation.service.cms.MmCmsArticlesContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmCmsArticlesContentQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsArticlesContentDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsArticlesContentVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

import static com.yj.reservation.entity.cms.MmCmsArticlesContent.ARTICLES_ID;

/**
 * <p>
 * CMS文章内容 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmCmsArticlesContentServiceImpl extends ServiceImpl<MmCmsArticlesContentMapper, MmCmsArticlesContent>
        implements MmCmsArticlesContentService {

    /**
    * 新增 CMS文章内容
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmCmsArticlesContent(MmCmsArticlesContentDTO dto){

        MmCmsArticlesContent entity = new MmCmsArticlesContent();

        QueryWrapper<MmCmsArticlesContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ARTICLES_ID, dto.getArticlesId());
        MmCmsArticlesContent existingEntity = super.getOne(queryWrapper,false);
//新增
        if(existingEntity ==null){
            BeanUtils.copyProperties(dto, entity);
            super.save(entity);
        }
        //修改
        else{
            existingEntity.setContent(dto.getContent());
            super.updateById(existingEntity);
        }

    }

    /**
    * 修改 CMS文章内容
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmCmsArticlesContent(MmCmsArticlesContentDTO dto){
        MmCmsArticlesContent entity = new MmCmsArticlesContent();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 CMS文章内容
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmCmsArticlesContent(Long articlesId){
        QueryWrapper<MmCmsArticlesContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ARTICLES_ID, articlesId);
        super.remove(queryWrapper);
//        MmCmsArticlesContent entity = super.getById()
//        super.removeById(id);
    }

    /**
    * 根据id获取 CMS文章内容 详情
    *
    * @param id 主键
    */
    @Override
    public MmCmsArticlesContentVO queryMmCmsArticlesContentById(Long articlesId){


        QueryWrapper<MmCmsArticlesContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ARTICLES_ID, articlesId);
        MmCmsArticlesContent entity = super.getOne(queryWrapper);

        MmCmsArticlesContentVO vo = new MmCmsArticlesContentVO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }


    /**
    * 分页查询 CMS文章内容
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmCmsArticlesContentVO> pageList(MmCmsArticlesContentQuery query) {
        Page<MmCmsArticlesContent> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmCmsArticlesContent>());
        List<MmCmsArticlesContent> list = page.getRecords();
        List<MmCmsArticlesContentVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmCmsArticlesContentVO vo = new MmCmsArticlesContentVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmCmsArticlesContentVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
    @Override
    public MmCmsArticlesContentVO findByActiclesId(Long articlesId) {
        QueryWrapper<MmCmsArticlesContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmCmsArticlesContent.ARTICLES_ID, articlesId);
        MmCmsArticlesContent entity = baseMapper.selectOne(queryWrapper);
        MmCmsArticlesContentVO vo = new MmCmsArticlesContentVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

}
