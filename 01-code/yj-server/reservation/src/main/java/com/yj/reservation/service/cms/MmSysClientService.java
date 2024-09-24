package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmSysClient;
import com.yj.reservation.pojo.cms.query.MmSysClientQuery;
import com.yj.reservation.pojo.cms.dto.MmSysClientDTO;
import com.yj.reservation.pojo.cms.vo.MmSysClientVO;


/**
* <p>
    * 客户端表 服务类
    * </p>
*
* @author yang
* @since 2024-03-11
*/
public interface MmSysClientService extends IService<MmSysClient> {

    /**
    * 新增 客户端表
    *
    * @param dto 参数
    */
    void addMmSysClient(MmSysClientDTO dto);

    /**
    * 修改 客户端表
    *
    * @param dto 参数
    */
    void modifyMmSysClient(MmSysClientDTO dto);

    /**
    * 删除 客户端表
    *
    * @param id 主键
    */
    void deleteMmSysClient(Long id);

    /**
    * 根据id获取 客户端表 详情
    *
    * @param id 主键
    */
    MmSysClientVO queryMmSysClientById(Long id);


    /**
    * 分页查询 客户端表
    *
    * @param query 参数
    * @return
    */
    Page<MmSysClientVO> pageList(MmSysClientQuery query);
}
