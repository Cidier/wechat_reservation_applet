package com.yj.reservation.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.entity.cms.MmCmsMenu;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.mapper.cms.MmCmsMenuMapper;
import com.yj.reservation.pojo.cms.vo.MmCmsMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuBusiness {

    private final  MmCmsMenuMapper mapper;

    //获取权限树(type=menu)
    public List<MmCmsMenuVO> menuTree() {
        List<MmCmsMenuVO> nodes = new ArrayList<>();
        //获取所有的权限列表 nodes
        QueryWrapper<MmCmsMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(MmCmsMenu.PARENT_ID);
//        queryWrapper.isNull(MMmCmsMenu.PARENT_ID);
//        queryWrapper.eq(MmCmsMenu.PARENT_ID,0);
//        queryWrapper.eq(MmCmsMenu.TYPE, "menu");
        List<MmCmsMenu> menuList = mapper.selectList(queryWrapper);
        for (MmCmsMenu node : menuList) {
            MmCmsMenuVO menuVO = new MmCmsMenuVO();
            BeanUtils.copyProperties(node, menuVO);
            List<MmCmsMenuVO> childs = menuTreeChilds(menuVO);
            menuVO.setChildrens(childs);
            nodes.add(menuVO);
        }
        return nodes;
    }

    //递归赋值
    private List<MmCmsMenuVO> menuTreeChilds(MmCmsMenuVO node) {
        List<MmCmsMenuVO> list = new ArrayList<>();
        //返回的子集合
        QueryWrapper<MmCmsMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmCmsMenu.PARENT_ID, node.getId());
//        queryWrapper.eq(MmCmsMenu.TYPE, "menu");
        List<MmCmsMenu> menuList = mapper.selectList(queryWrapper);
        if (menuList == null || menuList.size() <= 0) {
            return null;
        }
        for (MmCmsMenu p : menuList) {
            MmCmsMenuVO menuVO = new MmCmsMenuVO();
            BeanUtils.copyProperties(p, menuVO);
            list.add(menuVO);
            menuVO.setChildrens(menuTreeChilds(menuVO));
        }
        return list;
    }

    //获取权限树
    public List<MmCmsMenuVO> buildTreeGrid() {
        List<MmCmsMenuVO> nodes = new ArrayList<>();
        //获取所有的权限列表 nodes
        QueryWrapper<MmCmsMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNull(MmSysPermission.PARENT_ID);

//        queryWrapper.eq(MmCmsMenu.PARENT_ID,0);
                queryWrapper.isNull(MmCmsMenu.PARENT_ID);
//    queryWrapper.eq(Permission.TYPE,"menu");
        List<MmCmsMenu> menuList = mapper.selectList(queryWrapper);
        for (MmCmsMenu node : menuList) {
            MmCmsMenuVO menuVO = new MmCmsMenuVO();
            BeanUtils.copyProperties(node, menuVO);
            List<MmCmsMenuVO> childs = buildTreeGridChilds(menuVO);
            menuVO.setChildrens(childs);
            nodes.add(menuVO);
        }
        return nodes;
    }

    //递归赋值
    private List<MmCmsMenuVO> buildTreeGridChilds(MmCmsMenuVO node) {
        List<MmCmsMenuVO> list = new ArrayList<>();
        //返回的子集合
        QueryWrapper<MmCmsMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MmCmsMenu.PARENT_ID, node.getId());
        List<MmCmsMenu> menuList = mapper.selectList(queryWrapper);
        if (menuList == null || menuList.size() <= 0) {
            return null;
        }
        for (MmCmsMenu p : menuList) {
            MmCmsMenuVO menuVO = new MmCmsMenuVO();
            BeanUtils.copyProperties(p, menuVO);
            list.add(menuVO);
            menuVO.setChildrens(buildTreeGridChilds(menuVO));
        }
        return list;
    }
}
