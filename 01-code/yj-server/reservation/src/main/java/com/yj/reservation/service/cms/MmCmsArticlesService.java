package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmCmsArticles;
import com.yj.reservation.pojo.cms.query.MmCmsArticlesQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsArticlesDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsArticlesVO;


/**
* <p>
    * CMS文章 服务类
    * </p>
*
* @author yang
* @since 2024-01-18
*/
public interface MmCmsArticlesService extends IService<MmCmsArticles> {

    /**
    * 新增 CMS文章
    *
    * @param dto 参数
    */
    Long addMmCmsArticles(MmCmsArticlesDTO dto);

    /**
    * 修改 CMS文章
    *
    * @param dto 参数
    */
    void modifyMmCmsArticles(MmCmsArticlesDTO dto);

    /**
    * 删除 CMS文章
    *
    * @param id 主键
    */
    void deleteMmCmsArticles(Long id);

    /**
    * 根据id获取 CMS文章 详情
    *
    * @param id 主键
    */
    MmCmsArticlesVO queryMmCmsArticlesById(Long id);


    /**
    * 分页查询 CMS文章
    *
    * @param query 参数
    * @return
    */
    Page<MmCmsArticlesVO> pageList(MmCmsArticlesQuery query);
}
