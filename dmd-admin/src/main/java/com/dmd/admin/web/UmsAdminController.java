package com.dmd.admin.web;

import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.*;
import com.dmd.admin.model.dto.UmsAdminLoginParam;
import com.dmd.admin.model.dto.UmsAdminParam;
import com.dmd.admin.service.UmsAdminService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed();
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", umsAdmin.getIcon());
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(name, pageSize, pageNum);
        for (int i=0;i<adminList.size();i++) {
            adminList.get(i).setPassword(null);
            if (adminList.get(i).getUsername().equals("admin")){
                adminList.remove(i);
            }
        }
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id) {
        UmsAdmin admin = adminService.getItem(id);
        return CommonResult.success(admin);
    }

    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给用户分配+-权限")
    @RequestMapping(value = "/permission/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePermission(@RequestParam Long adminId,
                                         @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = adminService.updatePermission(adminId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }

    @ApiOperation("获取所有权限")
    @RequestMapping(value = "/allPermission", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<PageInfo> getAllPermission(@RequestBody BaseQuery baseQuery) {
        PageInfo pageInfo = adminService.getAllPermission(baseQuery);
        return WrapMapper.ok(pageInfo);
    }
    @ApiOperation("禁用和启用权限")
    @RequestMapping(value = "/isForbiddenPermission", method = RequestMethod.POST)
    @ResponseBody
    @OperationLog(content = "禁用和启用")
    public CommonResult<Integer> isForbiddenPermission(@RequestParam("id") Long id,@RequestParam("status") int status) {
        return CommonResult.success(adminService.isForbiddenPermission(id,status));
    }

    @ApiOperation("添加权限内容")
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> addPermission(@RequestBody UmsPermission umsPermission) {
        return CommonResult.success(adminService.addPermission(umsPermission));
    }

//    @ApiOperation("获取指定角色的权限")
//    @RequestMapping(value = "/roleForPermission", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<UmsPermission>> roleForPermission(@RequestParam("roleId") Long roleId) {
//        return CommonResult.success(adminService.roleForPermission(roleId));
//    }
    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/roleList", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<PageInfo> roleList(@RequestBody BaseQuery baseQuery) {
        PageInfo pageInfo = adminService.getRoleList(baseQuery);
        return  WrapMapper.ok(pageInfo);
    }
    @ApiOperation("建立权限和角色的关系")
    @RequestMapping(value = "/addPermissionForRole", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<Integer> addPermissionForRole(@RequestBody List<UmsRolePermissionRelation> permissionRelations) {
        return  WrapMapper.ok(adminService.addPermissionForRole(permissionRelations));
    }
    @ApiOperation("禁用和启用角色")
    @RequestMapping(value = "/isEnableRole", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<Long> isEnableRole(@RequestParam("id") Long id,@RequestParam("status") Long status) {
        return  WrapMapper.ok(adminService.isEnableRole(id,status));
    }
    @ApiOperation("添加角色")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<UmsRole> addRole(@RequestBody UmsRole umsRole) {
        return  WrapMapper.ok(adminService.addRole(umsRole));
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/deleteRoles", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<Integer> deleteRoles(@RequestBody List<Long> ids) {
        return  WrapMapper.ok(adminService.deleteRoles(ids));
    }
    @ApiOperation("修改角色信息")
    @RequestMapping(value = "/modifyRole", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<Integer> modifyRole(@RequestBody UmsRole umsRole) {
        return  WrapMapper.ok(adminService.modifyRole(umsRole));
    }
    @ApiOperation("建立角色和用户的对应关系")
    @RequestMapping(value = "/addRolesForAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<Integer> addRolesForAdmin(@RequestBody List<UmsAdminRoleRelation> adminRoleRelation) {
        return  WrapMapper.ok(adminService.addRolesForAdmin(adminRoleRelation));
    }
    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/updateAdminInfo", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<UmsAdmin> updateAdminInfo(@RequestBody UmsAdmin umsAdmin) {
        return  WrapMapper.ok(adminService.updateAdminInfo(umsAdmin));
    }
    @ApiOperation("删除用户信息")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<Integer> deleteUser(@RequestParam("id") Long id) {
        return  WrapMapper.ok(adminService.deleteUser(id));
    }

}
