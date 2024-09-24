package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysUser;
import com.yj.reservation.pojo.cms.query.MmSysUserQuery;
import com.yj.reservation.pojo.cms.dto.MmSysUserDTO;
import com.yj.reservation.pojo.cms.vo.MmSysUserVO;


/**
* <p>
    * 用户表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysUserService extends IService<MmSysUser> {

    /**
    * 新增 用户表
    *
    * @param dto 参数
    */
    void addMmSysUser(MmSysUserDTO dto);

    /**
    * 修改 用户表
    *
    * @param dto 参数
    */
    void modifyMmSysUser(MmSysUserDTO dto);

    /**
    * 删除 用户表
    *
    * @param id 主键
    */
    void deleteMmSysUser(Long id);

    /**
    * 根据id获取 用户表 详情
    *
    * @param id 主键
    */
    MmSysUserVO queryMmSysUserById(Long id);


    /**
    * 分页查询 用户表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysUserVO> pageList(MmSysUserQuery query);

    MmSysUser loadUserByUsername(String username);
}
