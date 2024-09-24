package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysDept;
import com.yj.reservation.pojo.cms.query.MmSysDeptQuery;
import com.yj.reservation.pojo.cms.dto.MmSysDeptDTO;
import com.yj.reservation.pojo.cms.vo.MmSysDeptVO;


/**
* <p>
    * 部门表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysDeptService extends IService<MmSysDept> {

    /**
    * 新增 部门表
    *
    * @param dto 参数
    */
    void addMmSysDept(MmSysDeptDTO dto);

    /**
    * 修改 部门表
    *
    * @param dto 参数
    */
    void modifyMmSysDept(MmSysDeptDTO dto);

    /**
    * 删除 部门表
    *
    * @param id 主键
    */
    void deleteMmSysDept(Long id);

    /**
    * 根据id获取 部门表 详情
    *
    * @param id 主键
    */
    MmSysDeptVO queryMmSysDeptById(Long id);


    /**
    * 分页查询 部门表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysDeptVO> pageList(MmSysDeptQuery query);
}
