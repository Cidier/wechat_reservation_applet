package com.yj.reservation.service.cms.impl;

import com.yj.reservation.entity.cms.MmCmsMenu;
import com.yj.reservation.mapper.cms.MmCmsMenuMapper;
import com.yj.reservation.service.cms.MmCmsMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmCmsMenuQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsMenuDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsMenuVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * CMS菜单配置 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-01-18
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmCmsMenuServiceImpl extends ServiceImpl<MmCmsMenuMapper, MmCmsMenu> implements MmCmsMenuService {

    /**
    * 新增 CMS菜单配置
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmCmsMenu(MmCmsMenuDTO dto){
        MmCmsMenu entity = new MmCmsMenu();
        BeanUtils.copyProperties(dto, entity);

        if(dto.getId()!=null){
            super.updateById(entity);
        }
        else{
            super.save(entity);
           Long id = entity.getId();

        }

    }

    /**
    * 修改 CMS菜单配置
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmCmsMenu(MmCmsMenuDTO dto){
        MmCmsMenu entity = new MmCmsMenu();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 CMS菜单配置
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmCmsMenu(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 CMS菜单配置 详情
    *
    * @param id 主键
    */
    @Override
    public MmCmsMenuVO queryMmCmsMenuById(Long id){
        MmCmsMenu entity = super.getById(id);
        MmCmsMenuVO vo = new MmCmsMenuVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 CMS菜单配置
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmCmsMenuVO> pageList(MmCmsMenuQuery query) {
        Page<MmCmsMenu> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmCmsMenu>());
        List<MmCmsMenu> list = page.getRecords();
        List<MmCmsMenuVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmCmsMenuVO vo = new MmCmsMenuVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmCmsMenuVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
