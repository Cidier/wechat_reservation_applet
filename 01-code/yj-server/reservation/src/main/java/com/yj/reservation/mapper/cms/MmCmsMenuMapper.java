package com.yj.reservation.mapper.cms;

import com.yj.reservation.entity.cms.MmCmsMenu;
import com.yj.reservation.common.mapper.ExtensionBaseMapper;
import com.yj.reservation.entity.cms.MmCmsMenu;
import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * CMS菜单配置 Mapper 接口
 * </p>
 *
 * @author yang
 */
public interface MmCmsMenuMapper extends ExtensionBaseMapper<MmCmsMenu> {
//    String innerJoinUserRoleRelationNotSQL = "select mm_cms_menu.id,mm_cms_menu.menu_title,mm_cms_menu.type,mm_cms_menu.sort_order,mm_cms_menu.parent_id,mm_cms_menu.url,mm_cms_menu.state from " +
//            MmCmsMenu.TABLE_NAME + " inner join " + MmSysRolePermissionRelation.TABLE_NAME + " on " + MmCmsMenu.ID_OF_TABLE + " = " + MmSysRolePermissionRelation.PERMISSION_ID_OF_TABLE;
//
//    String innerJoinUserRoleRelationSQL = innerJoinUserRoleRelationNotSQL + " where " + MmSysRolePermissionRelation.ROLE_ID + " in ${ids}";
//
//    @Select(innerJoinUserRoleRelationSQL)
//    List<MmCmsMenu> innerJoinUserRoleRelationByIds(@Param("ids") String ids);
//
//    @Select(innerJoinUserRoleRelationNotSQL)
//    List<MmCmsMenu> innerJoinUserRoleRelationList();
}
