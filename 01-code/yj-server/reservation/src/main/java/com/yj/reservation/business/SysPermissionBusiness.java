package com.yj.reservation.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.mapper.cms.MmSysPermissionMapper;
import com.yj.reservation.pojo.cms.vo.MmSysPermissionVO;
import com.yj.reservation.service.cms.MmSysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysPermissionBusiness {

    private final MmSysPermissionService service;
    private final MmSysPermissionMapper mapper;


    //获取权限树(type=menu)
    public List<MmSysPermissionVO> menuTree() {
        List<MmSysPermissionVO> nodes = new ArrayList<>();
        //获取所有的权限列表 nodes
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(MmSysPermission.PARENT_ID);
        queryWrapper.eq(MmSysPermission.TYPE, "menu");
        List<MmSysPermission> permissionList = mapper.selectList(queryWrapper);
        for (MmSysPermission node : permissionList) {
            MmSysPermissionVO permissionVO = new MmSysPermissionVO();
            BeanUtils.copyProperties(node, permissionVO);
            List<MmSysPermissionVO> childs = menuTreeChilds(permissionVO);
            permissionVO.setChildrens(childs);
            nodes.add(permissionVO);
        }
        return nodes;
    }

    //递归赋值
    private List<MmSysPermissionVO> menuTreeChilds(MmSysPermissionVO node) {
        List<MmSysPermissionVO> list = new ArrayList<>();
        //返回的子集合
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysPermission.PARENT_ID, node.getId());
        queryWrapper.eq(MmSysPermission.TYPE, "menu");
        List<MmSysPermission> permissionList = mapper.selectList(queryWrapper);
        if (permissionList == null || permissionList.size() <= 0) {
            return null;
        }
        for (MmSysPermission p : permissionList) {
            MmSysPermissionVO permissionVO = new MmSysPermissionVO();
            BeanUtils.copyProperties(p, permissionVO);
            list.add(permissionVO);
            permissionVO.setChildrens(menuTreeChilds(permissionVO));
        }
        return list;
    }

    //获取权限树
    public List<MmSysPermissionVO> buildTreeGrid() {
        List<MmSysPermissionVO> nodes = new ArrayList<>();
        //获取所有的权限列表 nodes
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(MmSysPermission.PARENT_ID);
//    queryWrapper.eq(Permission.TYPE,"menu");
        List<MmSysPermission> permissionList = mapper.selectList(queryWrapper);
        for (MmSysPermission node : permissionList) {
            MmSysPermissionVO permissionVO = new MmSysPermissionVO();
            BeanUtils.copyProperties(node, permissionVO);
            List<MmSysPermissionVO> childs = buildTreeGridChilds(permissionVO);
            permissionVO.setChildrens(childs);
            nodes.add(permissionVO);
        }
        return nodes;
    }

    //递归赋值
    private List<MmSysPermissionVO> buildTreeGridChilds(MmSysPermissionVO node) {
        List<MmSysPermissionVO> list = new ArrayList<>();
        //返回的子集合
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmSysPermission.PARENT_ID, node.getId());
        List<MmSysPermission> permissionList = mapper.selectList(queryWrapper);
        if (permissionList == null || permissionList.size() <= 0) {
            return null;
        }
        for (MmSysPermission p : permissionList) {
            MmSysPermissionVO permissionVO = new MmSysPermissionVO();
            BeanUtils.copyProperties(p, permissionVO);
            list.add(permissionVO);
            permissionVO.setChildrens(buildTreeGridChilds(permissionVO));
        }
        return list;
    }

}
