package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.pojo.cms.query.MmSysPermissionQuery;
import com.yj.reservation.pojo.cms.dto.MmSysPermissionDTO;
import com.yj.reservation.pojo.cms.vo.MenuVO;
import com.yj.reservation.pojo.cms.vo.MmSysPermissionVO;

import java.util.List;
import java.util.Set;


/**
* <p>
    * 权限表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysPermissionService extends IService<MmSysPermission> {

    /**
    * 新增 权限表
    *
    * @param dto 参数
    */
    void addMmSysPermission(MmSysPermissionDTO dto);

    /**
    * 修改 权限表
    *
    * @param dto 参数
    */
    void modifyMmSysPermission(MmSysPermissionDTO dto);

    /**
    * 删除 权限表
    *
    * @param id 主键
    */
    void deleteMmSysPermission(Long id);

    /**
    * 根据id获取 权限表 详情
    *
    * @param id 主键
    */
    MmSysPermissionVO queryMmSysPermissionById(Long id);


    /**
    * 分页查询 权限表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysPermissionVO> pageList(MmSysPermissionQuery query);

    List<MmSysPermission> listByUserRoleReletionSql(List<MmSysUserRoleRelation> userRoleRelList);

    List<MenuVO> listToMenuVO(Set<MmSysPermission> permissionSet);
}
