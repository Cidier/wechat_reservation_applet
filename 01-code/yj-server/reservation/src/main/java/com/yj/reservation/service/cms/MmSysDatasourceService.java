package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysDatasource;
import com.yj.reservation.pojo.cms.query.MmSysDatasourceQuery;
import com.yj.reservation.pojo.cms.dto.MmSysDatasourceDTO;
import com.yj.reservation.pojo.cms.vo.MmSysDatasourceVO;


/**
* <p>
    * 数据源配置表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysDatasourceService extends IService<MmSysDatasource> {

    /**
    * 新增 数据源配置表
    *
    * @param dto 参数
    */
    void addMmSysDatasource(MmSysDatasourceDTO dto);

    /**
    * 修改 数据源配置表
    *
    * @param dto 参数
    */
    void modifyMmSysDatasource(MmSysDatasourceDTO dto);

    /**
    * 删除 数据源配置表
    *
    * @param id 主键
    */
    void deleteMmSysDatasource(Long id);

    /**
    * 根据id获取 数据源配置表 详情
    *
    * @param id 主键
    */
    MmSysDatasourceVO queryMmSysDatasourceById(Long id);


    /**
    * 分页查询 数据源配置表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysDatasourceVO> pageList(MmSysDatasourceQuery query);
}
