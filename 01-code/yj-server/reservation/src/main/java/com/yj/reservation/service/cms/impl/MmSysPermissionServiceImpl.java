package com.yj.reservation.service.cms.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.util.LoggerUtil;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.mapper.cms.MmSysPermissionMapper;
import com.yj.reservation.pojo.cms.vo.MenuItemVO;
import com.yj.reservation.pojo.cms.vo.MenuVO;
import com.yj.reservation.service.cms.MmSysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.yj.reservation.pojo.cms.query.MmSysPermissionQuery;
import com.yj.reservation.pojo.cms.dto.MmSysPermissionDTO;
import com.yj.reservation.pojo.cms.vo.MmSysPermissionVO;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author yang
 * @since 2024-03-11
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class MmSysPermissionServiceImpl extends ServiceImpl<MmSysPermissionMapper, MmSysPermission>
        implements MmSysPermissionService {

    /**
     * 新增 权限表
     *
     * @param dto 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMmSysPermission(MmSysPermissionDTO dto){
        MmSysPermission entity = new MmSysPermission();
        BeanUtils.copyProperties(dto, entity);
        super.save(entity);
    }

    /**
     * 修改 权限表
     *
     * @param dto 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMmSysPermission(MmSysPermissionDTO dto){
        MmSysPermission entity = new MmSysPermission();
        BeanUtils.copyProperties(dto, entity);
        super.updateById(entity);
    }

    /**
     * 删除 权限表
     *
     * @param id 主键
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMmSysPermission(Long id){
        super.removeById(id);
    }

    /**
     * 根据id获取 权限表 详情
     *
     * @param id 主键
     */
    @Override
    public MmSysPermissionVO queryMmSysPermissionById(Long id){
        MmSysPermission entity = super.getById(id);
        MmSysPermissionVO vo = new MmSysPermissionVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    /**
     * 分页查询 权限表
     *
     * @param query 参数
     * @return
     */
    @Override
    public Page<MmSysPermissionVO> pageList(MmSysPermissionQuery query) {
        Page<MmSysPermission> page = page(new Page<>(query.getPageNo(), query.getPageSize()),
                new LambdaQueryWrapper<MmSysPermission>());
        List<MmSysPermission> list = page.getRecords();
        List<MmSysPermissionVO> resultList = new ArrayList<>();
        list.stream().forEach(bean -> {
            MmSysPermissionVO vo = new MmSysPermissionVO();
            BeanUtils.copyProperties(bean, vo);
            resultList.add(vo);
        });
        return new Page<MmSysPermissionVO>().setTotal(page.getTotal()).setRecords(resultList);
    }
    //获取权限树(type=menu)
    public List<MmSysPermissionVO> menuTree() {
        List<MmSysPermissionVO> nodes = new ArrayList<>();
        //获取所有的权限列表 nodes
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull(MmSysPermission.PARENT_ID);
        queryWrapper.eq(MmSysPermission.TYPE, "menu");
        List<MmSysPermission> permissionList = baseMapper.selectList(queryWrapper);
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
        List<MmSysPermission> permissionList = baseMapper.selectList(queryWrapper);
        if (permissionList == null || permissionList.size() <= 0) {
            return null;
        }
        for (MmSysPermission p : permissionList) {
            MmSysPermissionVO permissionVO = new MmSysPermissionVO();
            BeanUtils.copyProperties(node, permissionVO);
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
        List<MmSysPermission> permissionList = baseMapper.selectList(queryWrapper);
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
        List<MmSysPermission> permissionList = baseMapper.selectList(queryWrapper);
        if (permissionList == null || permissionList.size() <= 0) {
            return null;
        }
        for (MmSysPermission p : permissionList) {
            MmSysPermissionVO permissionVO = new MmSysPermissionVO();
            BeanUtils.copyProperties(node, permissionVO);
            list.add(permissionVO);
            permissionVO.setChildrens(buildTreeGridChilds(permissionVO));
        }
        return list;
    }

    //添加
    @Transactional
    public JsonResult insert(MmSysPermission permission) {

        baseMapper.insert(permission);
        return JsonResult.success();
    }

    //修改
    @Transactional
    public JsonResult update(MmSysPermission permission) {
        baseMapper.updateById(permission);
        return JsonResult.success();
    }

    //下拉框,根据type查list
    public List<MmSysPermission> listByType(String type) {
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(type)) {
            queryWrapper.like(MmSysPermission.TYPE, type);
        }
        return baseMapper.selectList(queryWrapper);
    }

    //修改弹窗查询回显
    public MmSysPermission permissionById(Long id) {
        QueryWrapper<MmSysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(MmSysPermission.ID, id);
        MmSysPermission permission = baseMapper.selectById(id);
        return permission;
    }

    public List<MmSysPermission> listByUserRoleReletionSql(List<MmSysUserRoleRelation> userRoleRelList) {
        String where = null;
        if (userRoleRelList != null) {
//      List<Long> roleIdList = new ArrayList<>();
            Set<Long> roleIdList = new HashSet<>();
            for (MmSysUserRoleRelation roleRelation : userRoleRelList) {
                roleIdList.add(roleRelation.getRoleId().longValue());
            }
            StringBuffer ids = new StringBuffer("(");
            roleIdList.stream().forEach(id -> ids.append(id).append(","));
            ids.delete(ids.length() - 1, ids.length());
            ids.append(")");
            where = ids.toString();
        }
        LoggerUtil.info("权限信息：" + where);
        if (where == null || where.equals(")") || where.equals("()")) {
            return baseMapper.innerJoinUserRoleRelationList();
        } else {
            return baseMapper.innerJoinUserRoleRelationByIds(where);
        }
    }

    public List<MenuVO> listToMenuVO(Set<MmSysPermission> permissionSet) {

        if (permissionSet == null) {
            return null;
        }
        List<MenuVO> result = new ArrayList<>();
        for (MmSysPermission permission : permissionSet) {
            if (permission.getParentId() == null) {
                MenuVO menu = new MenuVO();
                menu.setId(permission.getId());
                menu.setIndex(permission.getOd());
                menu.setTitle(permission.getName());
                menu.setMvo(menuItemRecursion(permissionSet, menu.getId()));
                result.add(menu);
            }
        }
        Collections.sort(result);
        return result;
    }

    private List<MenuItemVO> menuItemRecursion(Set<MmSysPermission> permissionSet, Long id) {
        List<MenuItemVO> result = new ArrayList<>();
        for (MmSysPermission permission : permissionSet) {
            if (permission.getParentId() != null && permission.getParentId() == id) {
                MenuItemVO vo = new MenuItemVO();
                vo.setId(permission.getId());
                vo.setIndex(permission.getOd());
                vo.setTitle(permission.getName());
                vo.setIcon(permission.getIcon());
                vo.setUrl(permission.getUrl());
                vo.setType(permission.getType());
                vo.setSubs(menuItemRecursion(permissionSet, permission.getId()));
                result.add(vo);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        //进行排序
        Collections.sort(result);
        return result;
    }

}
