package com.yj.reservation.service.cms.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.mapper.cms.MmSysUserRoleRelationMapper;
import com.yj.reservation.service.cms.MmSysUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysUserRoleRelationQuery;
import com.yj.reservation.pojo.cms.dto.MmSysUserRoleRelationDTO;
import com.yj.reservation.pojo.cms.vo.MmSysUserRoleRelationVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 角色用户关联表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysUserRoleRelationServiceImpl extends ServiceImpl<MmSysUserRoleRelationMapper, MmSysUserRoleRelation>
        implements MmSysUserRoleRelationService {

    /**
     * 新增 角色用户关联表
     *
     * @param dto 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysUserRoleRelation(MmSysUserRoleRelationDTO dto){
        MmSysUserRoleRelation entity = new MmSysUserRoleRelation();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
     * 修改 角色用户关联表
     *
     * @param dto 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysUserRoleRelation(MmSysUserRoleRelationDTO dto){
        MmSysUserRoleRelation entity = new MmSysUserRoleRelation();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
     * 删除 角色用户关联表
     *
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysUserRoleRelation(Long id){
        super.removeById(id);
    }

    /**
     * 根据id获取 角色用户关联表 详情
     *
     * @param id 主键
     */
    @Override
    public MmSysUserRoleRelationVO queryMmSysUserRoleRelationById(Long id){
        MmSysUserRoleRelation entity = super.getById(id);
        MmSysUserRoleRelationVO vo = new MmSysUserRoleRelationVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
     * 分页查询 角色用户关联表
     *
     * @param query 参数
     * @return
     */
    @Override
    public Page<MmSysUserRoleRelationVO> pageList(MmSysUserRoleRelationQuery query) {
        Page<MmSysUserRoleRelation> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
                new LambdaQueryWrapper<MmSysUserRoleRelation>());
        List<MmSysUserRoleRelation> list = page.getRecords();
        List<MmSysUserRoleRelationVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysUserRoleRelationVO vo = new MmSysUserRoleRelationVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysUserRoleRelationVO>().setTotal(page.getTotal()).setRecords(resultList);
    }

    public List<MmSysUserRoleRelation> listByUserId(Long userId) {
        QueryWrapper<MmSysUserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysUserRoleRelation.USER_ID, userId);
        List<MmSysUserRoleRelation> relationList = baseMapper.selectList(queryWrapper);
        return relationList;
    }
}
