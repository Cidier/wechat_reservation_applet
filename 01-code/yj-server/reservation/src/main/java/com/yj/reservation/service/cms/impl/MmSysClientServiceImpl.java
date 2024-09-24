package com.yj.reservation.service.cms.impl;

import com.yj.reservation.entity.cms.MmSysClient;
import com.yj.reservation.mapper.cms.MmSysClientMapper;
import com.yj.reservation.service.cms.MmSysClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysClientQuery;
import com.yj.reservation.pojo.cms.dto.MmSysClientDTO;
import com.yj.reservation.pojo.cms.vo.MmSysClientVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 客户端表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysClientServiceImpl extends ServiceImpl<MmSysClientMapper, MmSysClient>
        implements MmSysClientService {

    /**
    * 新增 客户端表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysClient(MmSysClientDTO dto){
        MmSysClient entity = new MmSysClient();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 客户端表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysClient(MmSysClientDTO dto){
        MmSysClient entity = new MmSysClient();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 客户端表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysClient(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 客户端表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysClientVO queryMmSysClientById(Long id){
        MmSysClient entity = super.getById(id);
        MmSysClientVO vo = new MmSysClientVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 客户端表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysClientVO> pageList(MmSysClientQuery query) {
        Page<MmSysClient> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysClient>());
        List<MmSysClient> list = page.getRecords();
        List<MmSysClientVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysClientVO vo = new MmSysClientVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysClientVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
