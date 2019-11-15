package com.dmd.admin.dao;

import com.dmd.admin.model.domain.UmsAdminRoleRelation;
import com.dmd.admin.model.domain.UmsPermission;
import com.dmd.admin.model.domain.UmsRole;
import com.dmd.admin.model.domain.UmsRolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.security.Permission;
import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * Created by macro on 2018/10/8.
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
    /**
     * 添加角色
     */
    int addRole(UmsRole umsRole);
    /**
     * 建立用户和角色对应关系
     */
    int addRolesForAdmin(List<UmsAdminRoleRelation> adminRoleRelation);
    /**
     * 删除用户和角色对应关系
     */
    int deleteRolesForAdmin(@Param("adminId") String adminId);

    /**
     * 添加权限
     */
    int addPermissions(UmsPermission Permission);
    /**
     * 建立角色和权限的对应关系
     */
    int addPermissionForRole(@Param("permissionRelations") List<UmsRolePermissionRelation> permissionRelations);
    /**
     * 删除角色和权限的对应关系
     */
    int deletePermissionForRole(@Param("roleId") Long roleId);

    List<UmsPermission> getAllPermission();

    int isForbiddenPermission(@Param("id") Long id,@Param("status") int status);

    List<UmsPermission> roleForPermission(@Param("roleId") Long roleId);

    List<UmsRole> roleList();

    //是否启用角色
    int isEnableRole(@Param("id") Long id,@Param("status") Long status);
    //删除角色
    int deleteRoles(@Param("ids") List<Long> ids);
    //修改角色信息
    int modifyRole(UmsRole umsRole);

}
