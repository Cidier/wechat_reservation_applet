package com.yj.reservation.service.cms.impl;

import com.yj.reservation.entity.cms.MmSysDept;
import com.yj.reservation.mapper.cms.MmSysDeptMapper;
import com.yj.reservation.service.cms.MmSysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysDeptQuery;
import com.yj.reservation.pojo.cms.dto.MmSysDeptDTO;
import com.yj.reservation.pojo.cms.vo.MmSysDeptVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysDeptServiceImpl extends ServiceImpl<MmSysDeptMapper, MmSysDept>
        implements MmSysDeptService {

    /**
    * 新增 部门表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysDept(MmSysDeptDTO dto){
        MmSysDept entity = new MmSysDept();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 部门表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysDept(MmSysDeptDTO dto){
        MmSysDept entity = new MmSysDept();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 部门表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysDept(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 部门表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysDeptVO queryMmSysDeptById(Long id){
        MmSysDept entity = super.getById(id);
        MmSysDeptVO vo = new MmSysDeptVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 部门表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysDeptVO> pageList(MmSysDeptQuery query) {
        Page<MmSysDept> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysDept>());
        List<MmSysDept> list = page.getRecords();
        List<MmSysDeptVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysDeptVO vo = new MmSysDeptVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysDeptVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
