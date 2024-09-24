package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmCmsMenu;
import com.yj.reservation.pojo.cms.query.MmCmsMenuQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsMenuDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsMenuVO;


/**
* <p>
    * CMS菜单配置 服务类
    * </p>
*
* @author yang
* @since 2024-01-18
*/
public interface MmCmsMenuService extends IService<MmCmsMenu> {

    /**
    * 新增 CMS菜单配置
    *
    * @param dto 参数
    */
    void addMmCmsMenu(MmCmsMenuDTO dto);

    /**
    * 修改 CMS菜单配置
    *
    * @param dto 参数
    */
    void modifyMmCmsMenu(MmCmsMenuDTO dto);

    /**
    * 删除 CMS菜单配置
    *
    * @param id 主键
    */
    void deleteMmCmsMenu(Long id);

    /**
    * 根据id获取 CMS菜单配置 详情
    *
    * @param id 主键
    */
    MmCmsMenuVO queryMmCmsMenuById(Long id);


    /**
    * 分页查询 CMS菜单配置
    *
    * @param query 参数
    * @return
    */
    Page<MmCmsMenuVO> pageList(MmCmsMenuQuery query);


}
