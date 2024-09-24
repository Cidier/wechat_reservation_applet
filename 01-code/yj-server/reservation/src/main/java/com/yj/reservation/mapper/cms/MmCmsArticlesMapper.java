package com.yj.reservation.mapper.cms;

import com.yj.reservation.entity.cms.MmCmsArticles;
import com.yj.reservation.common.mapper.ExtensionBaseMapper;
import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * CMS文章 Mapper 接口
 * </p>
 *
 * @author yang
 */
public interface MmCmsArticlesMapper extends ExtensionBaseMapper<MmCmsArticles> {
//    String innerJoinUserRoleRelationNotSQL = "select mm_cms_articles.id,mm_cms_articles.title,mm_cms_articles.type,mm_cms_articles.sortOrder," +
//            "mm_cms_articles.state,mm_cms_articles.icon,mm_cms_articles.parent_ids,mm_cms_articles.precode from " +
//            MmSysPermission.TABLE_NAME + " inner join " + MmSysRolePermissionRelation.TABLE_NAME + " on " + MmSysPermission.ID_OF_TABLE + " = " + MmSysRolePermissionRelation.PERMISSION_ID_OF_TABLE;
//
//    String innerJoinUserRoleRelationSQL = innerJoinUserRoleRelationNotSQL + " where " + MmSysRolePermissionRelation.ROLE_ID + " in ${ids}";
//
//    @Select(innerJoinUserRoleRelationSQL)
//    List<MmSysPermission> innerJoinUserRoleRelationByIds(@Param("ids") String ids);
//
//    @Select(innerJoinUserRoleRelationNotSQL)
//    List<MmSysPermission> innerJoinUserRoleRelationList();
}
