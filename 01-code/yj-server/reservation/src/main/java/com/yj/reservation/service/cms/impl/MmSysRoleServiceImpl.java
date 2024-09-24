package com.yj.reservation.service.cms.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.entity.cms.MmSysRole;
import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.mapper.cms.MmSysPermissionMapper;
import com.yj.reservation.mapper.cms.MmSysRoleMapper;
import com.yj.reservation.mapper.cms.MmSysRolePermissionRelationMapper;
import com.yj.reservation.mapper.cms.MmSysUserRoleRelationMapper;
import com.yj.reservation.service.cms.MmSysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysRoleQuery;
import com.yj.reservation.pojo.cms.dto.MmSysRoleDTO;
import com.yj.reservation.pojo.cms.vo.MmSysRoleVO;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysRoleServiceImpl extends ServiceImpl<MmSysRoleMapper, MmSysRole>
        implements MmSysRoleService {

    private final MmSysRolePermissionRelationMapper rolePermissionRelationMapper;
    private final MmSysUserRoleRelationMapper userRoleRelationMapper;
    private final MmSysPermissionMapper permissionMapper;

    /**
    * 新增 角色表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysRole(MmSysRoleDTO dto){
        MmSysRole entity = new MmSysRole();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
    * 修改 角色表
    *
    * @param dto 参数
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysRole(MmSysRoleDTO dto){
        MmSysRole entity = new MmSysRole();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
    * 删除 角色表
    *
    * @param id 主键
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysRole(Long id){
        super.removeById(id);
    }

    /**
    * 根据id获取 角色表 详情
    *
    * @param id 主键
    */
    @Override
    public MmSysRoleVO queryMmSysRoleById(Long id){
        MmSysRole entity = super.getById(id);
        MmSysRoleVO vo = new MmSysRoleVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
    * 分页查询 角色表
    *
    * @param query 参数
    * @return
    */
    @Override
    public Page<MmSysRoleVO> pageList(MmSysRoleQuery query) {
        Page<MmSysRole> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
        new LambdaQueryWrapper<MmSysRole>());
        List<MmSysRole> list = page.getRecords();
        List<MmSysRoleVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysRoleVO vo = new MmSysRoleVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysRoleVO>().setTotal(page.getTotal()).setRecords(resultList);
    }


    @Transactional
    public void delete(Integer id) {
        baseMapper.deleteById(id);
        QueryWrapper<MmSysRolePermissionRelation> rolePermissionRelationQueryWrapper = new QueryWrapper<>();
        rolePermissionRelationQueryWrapper.eq(MmSysRolePermissionRelation.ROLE_ID, id);
        rolePermissionRelationMapper.delete(rolePermissionRelationQueryWrapper);
        QueryWrapper<MmSysUserRoleRelation> userRoleRelationQueryWrapper = new QueryWrapper();
        userRoleRelationQueryWrapper.eq(MmSysUserRoleRelation.ROLE_ID, id);
        userRoleRelationMapper.delete(userRoleRelationQueryWrapper);
    }

    @Transactional
    public void save(MmSysRoleVO roleVO) {
        if (roleVO.getId() != null) {
            //修改
            MmSysRole role = baseMapper.selectById(roleVO.getId());
            role.setName(roleVO.getName());
            role.setState(roleVO.getState());
            role.setId(roleVO.getId());
            role.setRemarks(roleVO.getRemarks());
            baseMapper.updateById(role);
            QueryWrapper<MmSysRolePermissionRelation> rolePermissionRelationQueryWrapper = new QueryWrapper<>();
            rolePermissionRelationQueryWrapper.eq(MmSysRolePermissionRelation.ROLE_ID, role.getId());
            rolePermissionRelationMapper.delete(rolePermissionRelationQueryWrapper);
            insertRolePermissionRelation(role, roleVO);
        } else {
            //添加
            MmSysRole role = new MmSysRole();
            role.setName(roleVO.getName());
            role.setState(roleVO.getState());
            role.setRemarks(roleVO.getRemarks());
            baseMapper.insert(role);
            insertRolePermissionRelation(role, roleVO);
        }
    }

    @Transactional
    //添加中间表
    public void insertRolePermissionRelation(MmSysRole role, MmSysRoleVO roleVO) {
        Long[] ids = roleVO.getPermissionIds();
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            if (!list.contains(ids[i])) {
                list.add(ids[i]);
            }
        }
        Long[] permissionIds = list.toArray(new Long[list.size()]);
        for (int a = 0; a < permissionIds.length; a++) {
            MmSysRolePermissionRelation rolePermission = new MmSysRolePermissionRelation();
            Long id = permissionIds[a];
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(id);
            rolePermissionRelationMapper.insert(rolePermission);
        }
    }

    @Transactional
    //修改弹窗，根据roleId 查询的中间表cascader
    public List<List<Long>> rolePermissionRelationById(Long roleId) {
        QueryWrapper<MmSysRolePermissionRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysRolePermissionRelation.ROLE_ID, roleId);
        List<MmSysRolePermissionRelation> rolePermissionRelations = rolePermissionRelationMapper.selectList(queryWrapper);
        List<List<Long>> lists = new ArrayList<>();
        for (MmSysRolePermissionRelation r : rolePermissionRelations) {
            MmSysPermission permission = permissionMapper.selectById(r.getPermissionId());
//            if (permission.getType().equals("permission")) {
            List<Long> list = permissionParentId(permission, new ArrayList<>());
            Collections.reverse(list);
            lists.add(list);
//            }
        }
        return lists;
    }

    //递归查询根据permission查询父id，返回pid集合
    public List<Long> permissionParentId(MmSysPermission permission, List<Long> pids) {
        pids.add(permission.getId());
        QueryWrapper<MmSysPermission> pidWrapper = new QueryWrapper<>();
        pidWrapper.eq(MmSysPermission.ID, permission.getParentId());//将pid赋为id
        MmSysPermission parentPermission = permissionMapper.selectOne(pidWrapper);
        if (parentPermission != null) {
            permissionParentId(parentPermission, pids);
        }
        return pids;
    }

    public MmSysRole roleById(Integer id) {
        QueryWrapper<MmSysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(MmSysRole.ID, id);
        MmSysRole role = baseMapper.selectById(id);
        return role;
    }
}
