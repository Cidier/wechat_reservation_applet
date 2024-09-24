package com.yj.reservation.mapper.cms;

import com.yj.reservation.entity.cms.MmSysPermission;
import com.yj.reservation.common.mapper.ExtensionBaseMapper;
import com.yj.reservation.entity.cms.MmSysRolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author yang
 */
public interface MmSysPermissionMapper extends ExtensionBaseMapper<MmSysPermission> {
    String innerJoinUserRoleRelationNotSQL = "select mm_sys_permission.id,mm_sys_permission.name," +
            "mm_sys_permission.type,mm_sys_permission.od,mm_sys_permission.parent_id,mm_sys_permission.url," +
            "mm_sys_permission.route_url,mm_sys_permission.state,mm_sys_permission.icon,mm_sys_permission.parent_ids,mm_sys_permission.precode from " +
            MmSysPermission.TABLE_NAME + " inner join " + MmSysRolePermissionRelation.TABLE_NAME + " on " + MmSysPermission.ID_OF_TABLE + " = " + MmSysRolePermissionRelation.PERMISSION_ID_OF_TABLE;

    String innerJoinUserRoleRelationSQL = innerJoinUserRoleRelationNotSQL + " where " + MmSysRolePermissionRelation.ROLE_ID + " in ${ids}";

    @Select(innerJoinUserRoleRelationSQL)
    List<MmSysPermission> innerJoinUserRoleRelationByIds(@Param("ids") String ids);

    @Select(innerJoinUserRoleRelationNotSQL)
    List<MmSysPermission> innerJoinUserRoleRelationList();
}
