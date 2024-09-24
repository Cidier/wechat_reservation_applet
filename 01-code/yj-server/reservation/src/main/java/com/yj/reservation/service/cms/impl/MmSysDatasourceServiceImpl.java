package com.yj.reservation.service.cms.impl;

import com.yj.reservation.entity.cms.MmSysDatasource;
import com.yj.reservation.mapper.cms.MmSysDatasourceMapper;
import com.yj.reservation.service.cms.MmSysDatasourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysDatasourceQuery;
import com.yj.reservation.pojo.cms.dto.MmSysDatasourceDTO;
import com.yj.reservation.pojo.cms.vo.MmSysDatasourceVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 数据源配置表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysDatasourceServiceImpl extends ServiceImpl<MmSysDatasourceMapper, MmSysDatasource>
        implements MmSysDatasourceService {

    /**
    * 新增 数据源配置表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysDatasource(MmSysDatasourceDTO dto){
        MmSysDatasource entity = new MmSysDatasource();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 数据源配置表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysDatasource(MmSysDatasourceDTO dto){
        MmSysDatasource entity = new MmSysDatasource();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 数据源配置表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysDatasource(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 数据源配置表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysDatasourceVO queryMmSysDatasourceById(Long id){
        MmSysDatasource entity = super.getById(id);
        MmSysDatasourceVO vo = new MmSysDatasourceVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 数据源配置表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysDatasourceVO> pageList(MmSysDatasourceQuery query) {
        Page<MmSysDatasource> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysDatasource>());
        List<MmSysDatasource> list = page.getRecords();
        List<MmSysDatasourceVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysDatasourceVO vo = new MmSysDatasourceVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysDatasourceVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
