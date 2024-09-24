package com.yj.reservation.service.cms.impl;

import com.yj.reservation.entity.cms.MmSysTenant;
import com.yj.reservation.mapper.cms.MmSysTenantMapper;
import com.yj.reservation.service.cms.MmSysTenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysTenantQuery;
import com.yj.reservation.pojo.cms.dto.MmSysTenantDTO;
import com.yj.reservation.pojo.cms.vo.MmSysTenantVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysTenantServiceImpl extends ServiceImpl<MmSysTenantMapper, MmSysTenant>
        implements MmSysTenantService {

    /**
    * 新增 租户表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysTenant(MmSysTenantDTO dto){
        MmSysTenant entity = new MmSysTenant();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 租户表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysTenant(MmSysTenantDTO dto){
        MmSysTenant entity = new MmSysTenant();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 租户表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysTenant(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 租户表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysTenantVO queryMmSysTenantById(Long id){
        MmSysTenant entity = super.getById(id);
        MmSysTenantVO vo = new MmSysTenantVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 租户表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysTenantVO> pageList(MmSysTenantQuery query) {
        Page<MmSysTenant> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysTenant>());
        List<MmSysTenant> list = page.getRecords();
        List<MmSysTenantVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysTenantVO vo = new MmSysTenantVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysTenantVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
