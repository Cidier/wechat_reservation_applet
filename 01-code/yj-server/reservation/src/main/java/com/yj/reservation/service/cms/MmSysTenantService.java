package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysTenant;
import com.yj.reservation.pojo.cms.query.MmSysTenantQuery;
import com.yj.reservation.pojo.cms.dto.MmSysTenantDTO;
import com.yj.reservation.pojo.cms.vo.MmSysTenantVO;


/**
* <p>
    * 租户表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysTenantService extends IService<MmSysTenant> {

    /**
    * 新增 租户表
    *
    * @param dto 参数
    */
    void addMmSysTenant(MmSysTenantDTO dto);

    /**
    * 修改 租户表
    *
    * @param dto 参数
    */
    void modifyMmSysTenant(MmSysTenantDTO dto);

    /**
    * 删除 租户表
    *
    * @param id 主键
    */
    void deleteMmSysTenant(Long id);

    /**
    * 根据id获取 租户表 详情
    *
    * @param id 主键
    */
    MmSysTenantVO queryMmSysTenantById(Long id);


    /**
    * 分页查询 租户表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysTenantVO> pageList(MmSysTenantQuery query);
}
