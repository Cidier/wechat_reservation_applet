package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysUserRoleRelation;
import com.yj.reservation.pojo.cms.query.MmSysUserRoleRelationQuery;
import com.yj.reservation.pojo.cms.dto.MmSysUserRoleRelationDTO;
import com.yj.reservation.pojo.cms.vo.MmSysUserRoleRelationVO;

import java.util.List;


/**
* <p>
    * 角色用户关联表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysUserRoleRelationService extends IService<MmSysUserRoleRelation> {

    /**
    * 新增 角色用户关联表
    *
    * @param dto 参数
    */
    void addMmSysUserRoleRelation(MmSysUserRoleRelationDTO dto);

    /**
    * 修改 角色用户关联表
    *
    * @param dto 参数
    */
    void modifyMmSysUserRoleRelation(MmSysUserRoleRelationDTO dto);

    /**
    * 删除 角色用户关联表
    *
    * @param id 主键
    */
    void deleteMmSysUserRoleRelation(Long id);

    /**
    * 根据id获取 角色用户关联表 详情
    *
    * @param id 主键
    */
    MmSysUserRoleRelationVO queryMmSysUserRoleRelationById(Long id);


    /**
    * 分页查询 角色用户关联表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysUserRoleRelationVO> pageList(MmSysUserRoleRelationQuery query);

    List<MmSysUserRoleRelation> listByUserId(Long userId);
}
