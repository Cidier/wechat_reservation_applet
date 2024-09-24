package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import com.yj.reservation.pojo.cms.query.MmSysRolePermissionRelationQuery;
import com.yj.reservation.pojo.cms.dto.MmSysRolePermissionRelationDTO;
import com.yj.reservation.pojo.cms.vo.MmSysRolePermissionRelationVO;


/**
* <p>
    * 角色权限关联表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysRolePermissionRelationService extends IService<MmSysRolePermissionRelation> {

    /**
    * 新增 角色权限关联表
    *
    * @param dto 参数
    */
    void addMmSysRolePermissionRelation(MmSysRolePermissionRelationDTO dto);

    /**
    * 修改 角色权限关联表
    *
    * @param dto 参数
    */
    void modifyMmSysRolePermissionRelation(MmSysRolePermissionRelationDTO dto);

    /**
    * 删除 角色权限关联表
    *
    * @param id 主键
    */
    void deleteMmSysRolePermissionRelation(Long id);

    /**
    * 根据id获取 角色权限关联表 详情
    *
    * @param id 主键
    */
    MmSysRolePermissionRelationVO queryMmSysRolePermissionRelationById(Long id);


    /**
    * 分页查询 角色权限关联表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysRolePermissionRelationVO> pageList(MmSysRolePermissionRelationQuery query);
}
