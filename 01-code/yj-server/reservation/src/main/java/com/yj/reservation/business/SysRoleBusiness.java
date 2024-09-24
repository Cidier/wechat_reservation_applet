package com.yj.reservation.business;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.entity.cms.MmSysRole;
import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import com.yj.reservation.mapper.cms.MmSysPermissionMapper;
import com.yj.reservation.mapper.cms.MmSysRoleMapper;
import com.yj.reservation.mapper.cms.MmSysRolePermissionRelationMapper;
import com.yj.reservation.pojo.cms.dto.MmSysRoleDTO;
import com.yj.reservation.service.cms.MmSysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysRoleBusiness {

    private final MmSysRoleService service;
    private final MmSysRoleMapper mapper;
    private final MmSysRolePermissionRelationMapper rolePermissionRelationMapper;
    private final MmSysPermissionMapper permissionMapper;

    @Transactional
    public void save(MmSysRoleDTO dto) {
        if (dto.getId() != null) {
            //修改
            MmSysRole role = mapper.selectById(dto.getId());
            role.setName(dto.getName());
            role.setState(dto.getState());
            role.setId(dto.getId());
            role.setRemarks(dto.getRemarks());
            mapper.updateById(role);
            QueryWrapper<MmSysRolePermissionRelation> rolePermissionRelationQueryWrapper = new QueryWrapper<>();
            rolePermissionRelationQueryWrapper.eq(MmSysRolePermissionRelation.ROLE_ID, role.getId());
            rolePermissionRelationMapper.delete(rolePermissionRelationQueryWrapper);
            insertRolePermissionRelation(role, dto);
        } else {
            //添加
            MmSysRole role = new MmSysRole();
            role.setName(dto.getName());
            role.setState(dto.getState());
            role.setRemarks(dto.getRemarks());
            mapper.insert(role);
            insertRolePermissionRelation(role, dto);
        }
    }

    @Transactional
    //添加中间表
    public void insertRolePermissionRelation(MmSysRole role, MmSysRoleDTO dto) {
        Long[] ids = dto.getPermissionIds();
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
        MmSysRole role = mapper.selectById(id);
        return role;
    }



}
