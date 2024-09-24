package com.yj.reservation.service.cms.impl;

import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import com.yj.reservation.mapper.cms.MmSysRolePermissionRelationMapper;
import com.yj.reservation.service.cms.MmSysRolePermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysRolePermissionRelationQuery;
import com.yj.reservation.pojo.cms.dto.MmSysRolePermissionRelationDTO;
import com.yj.reservation.pojo.cms.vo.MmSysRolePermissionRelationVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysRolePermissionRelationServiceImpl extends ServiceImpl<MmSysRolePermissionRelationMapper, MmSysRolePermissionRelation>
        implements MmSysRolePermissionRelationService {

    /**
    * 新增 角色权限关联表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysRolePermissionRelation(MmSysRolePermissionRelationDTO dto){
        MmSysRolePermissionRelation entity = new MmSysRolePermissionRelation();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 角色权限关联表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysRolePermissionRelation(MmSysRolePermissionRelationDTO dto){
        MmSysRolePermissionRelation entity = new MmSysRolePermissionRelation();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 角色权限关联表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysRolePermissionRelation(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 角色权限关联表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysRolePermissionRelationVO queryMmSysRolePermissionRelationById(Long id){
        MmSysRolePermissionRelation entity = super.getById(id);
        MmSysRolePermissionRelationVO vo = new MmSysRolePermissionRelationVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 角色权限关联表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysRolePermissionRelationVO> pageList(MmSysRolePermissionRelationQuery query) {
        Page<MmSysRolePermissionRelation> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysRolePermissionRelation>());
        List<MmSysRolePermissionRelation> list = page.getRecords();
        List<MmSysRolePermissionRelationVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysRolePermissionRelationVO vo = new MmSysRolePermissionRelationVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysRolePermissionRelationVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
}
