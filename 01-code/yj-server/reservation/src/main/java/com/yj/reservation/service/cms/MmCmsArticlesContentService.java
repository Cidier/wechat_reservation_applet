package com.yj.reservation.service.cms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.reservation.entity.cms.MmCmsArticlesContent;
import com.yj.reservation.pojo.cms.query.MmCmsArticlesContentQuery;
import com.yj.reservation.pojo.cms.dto.MmCmsArticlesContentDTO;
import com.yj.reservation.pojo.cms.vo.MmCmsArticlesContentVO;


/**
* <p>
    * CMS文章内容 服务类
    * </p>
*
* @author yang
* @since 2024-01-18
*/
public interface MmCmsArticlesContentService extends IService<MmCmsArticlesContent> {

    /**
    * 新增 CMS文章内容
    *
    * @param dto 参数
    */
    void addMmCmsArticlesContent(MmCmsArticlesContentDTO dto);

    /**
    * 修改 CMS文章内容
    *
    * @param dto 参数
    */
    void modifyMmCmsArticlesContent(MmCmsArticlesContentDTO dto);

    /**
    * 删除 CMS文章内容
    *
    * @param id 主键
    */
    void deleteMmCmsArticlesContent(Long id);

    /**
    * 根据id获取 CMS文章内容 详情
    *
    * @param id 主键
    */
    MmCmsArticlesContentVO queryMmCmsArticlesContentById(Long id);


    /**
    * 分页查询 CMS文章内容
    *
    * @param query 参数
    * @return
    */
    Page<MmCmsArticlesContentVO> pageList(MmCmsArticlesContentQuery query);


    MmCmsArticlesContentVO findByActiclesId(Long articlesId);
}
