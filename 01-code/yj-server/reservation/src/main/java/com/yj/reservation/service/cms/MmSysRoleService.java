package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysRole;
import com.yj.reservation.pojo.cms.query.MmSysRoleQuery;
import com.yj.reservation.pojo.cms.dto.MmSysRoleDTO;
import com.yj.reservation.pojo.cms.vo.MmSysRoleVO;


/**
* <p>
    * 角色表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysRoleService extends IService<MmSysRole> {

    /**
    * 新增 角色表
    *
    * @param dto 参数
    */
    void addMmSysRole(MmSysRoleDTO dto);

    /**
    * 修改 角色表
    *
    * @param dto 参数
    */
    void modifyMmSysRole(MmSysRoleDTO dto);

    /**
    * 删除 角色表
    *
    * @param id 主键
    */
    void deleteMmSysRole(Long id);

    /**
    * 根据id获取 角色表 详情
    *
    * @param id 主键
    */
    MmSysRoleVO queryMmSysRoleById(Long id);


    /**
    * 分页查询 角色表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysRoleVO> pageList(MmSysRoleQuery query);
}
