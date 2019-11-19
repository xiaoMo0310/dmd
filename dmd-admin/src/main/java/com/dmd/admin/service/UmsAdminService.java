package com.dmd.admin.service;

import com.dmd.admin.model.domain.*;
import com.dmd.admin.model.dto.UmsAdminParam;
import com.dmd.base.dto.BaseQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(String name, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 修改用户的+-权限
     */
    @Transactional
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 所有权限列表
    */
    PageInfo getAllPermission(BaseQuery baseQuery);

    /**
     * 所有权限列表
     */
    int isForbiddenPermission(Long id,int status);

    /**
     * 添加权限
     */
    int addPermission(UmsPermission umsPermission);
    /**
     * 查询角色拥有的权限
     */
    List<UmsPermission> roleForPermission(Long roleId);
    /**
     * 查询所有角色
     */
    PageInfo getRoleList(BaseQuery baseQuery);
    /**
     * 建立角色和权限的关系
     */
    int addPermissionForRole(List<UmsRolePermissionRelation> permissionRelations);
    //是否启用角色
    Long isEnableRole(Long id,Long status);
    //添加角色
    UmsRole addRole(UmsRole umsRole);

    //删除角色
    int deleteRoles(List<Long> ids);

    //修改角色信息
    int modifyRole(UmsRole umsRole);

    //建立角色和用户的对应关系
    int addRolesForAdmin(List<UmsAdminRoleRelation> adminRoleRelation);
}
