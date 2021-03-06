package com.dmd.admin.service.impl;

import com.dmd.admin.bo.AdminUserDetails;
import com.dmd.admin.dao.UmsAdminPermissionRelationDao;
import com.dmd.admin.dao.UmsAdminRoleRelationDao;
import com.dmd.admin.mapper.UmsAdminLoginLogMapper;
import com.dmd.admin.mapper.UmsAdminMapper;
import com.dmd.admin.mapper.UmsAdminPermissionRelationMapper;
import com.dmd.admin.mapper.UmsAdminRoleRelationMapper;
import com.dmd.admin.model.domain.*;
import com.dmd.admin.model.dto.UmsAdminParam;
import com.dmd.admin.service.UmsAdminService;
import com.dmd.admin.utils.JwtTokenUtil;
import com.dmd.base.dto.BaseQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UmsAdminService实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;
    @Autowired
    private UmsAdminPermissionRelationDao adminPermissionRelationDao;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            AdminUserDetails userDetails = (AdminUserDetails) userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                return "用户名或密码不正确";
            }
            if(userDetails.getUmsAdmin().getStatus().equals(0)){
                return "用户被禁用";
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin record = new UmsAdmin();
        record.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String name, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andUsernameLike("%" + name + "%");
            example.or(example.createCriteria().andNickNameLike("%" + name + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        //密码已经加密处理，需要单独修改
        admin.setPassword(null);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int delete(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        //删除原所有权限关系
        UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        List<Long> rolePermissionList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(adminId,1,addPermissionIdList));
            relationList.addAll(convert(adminId,-1,subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UmsAdminPermissionRelation> convert(Long adminId,Integer type,List<Long> permissionIdList) {
        List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        List<UmsPermission> list=adminRoleRelationDao.getPermissionList(adminId);
        UmsPermission u=new UmsPermission();
        u.setId(0L);
        return getPer(list,u);
    }
    public List<UmsPermission> getPer(List<UmsPermission> list,UmsPermission u){
        List<UmsPermission> newList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if (list.get(i).getPid()==u.getId()){
                newList.add(list.get(i));
                u.setChildren(newList);
                getPer(list,list.get(i));
            }
        }
        return newList;
    }
    @Override
    public PageInfo getAllPermission(BaseQuery baseQuery,String type) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UmsPermission> umsPermissions=adminRoleRelationDao.getAllPermission(type);
        //List<UmsPermission> rolePermission=adminRoleRelationDao.roleForPermission(baseQuery.getRoleId());
        //用于判断所有权限中角色的权限
        /*for (UmsPermission umsPermission:umsPermissions){
            for (UmsPermission umsPermission1: rolePermission){
                if (umsPermission.getName().equals(umsPermission1.getName())){
                    umsPermission.setBeCheck(true);
                }
            }
        }*/

        if(type.equals("1")){
            UmsPermission u=new UmsPermission();
            u.setId(0L);
            umsPermissions=getPer(umsPermissions,u);
        }

        return new PageInfo<>(umsPermissions);
    }

    @Override
    public int isForbiddenPermission(Long id, int status) {
        return adminRoleRelationDao.isForbiddenPermission(id,status);
    }

    @Override
    public int addPermission(UmsPermission umsPermission) {
        return adminRoleRelationDao.addPermissions(umsPermission);
    }

    @Override
    public List<UmsPermission> roleForPermission(Long roleId) {
        return adminRoleRelationDao.roleForPermission(roleId);
    }

    @Override
    public PageInfo getRoleList(BaseQuery baseQuery,String type) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UmsRole> umsRolesForAmin=new ArrayList<>();
        List<UmsRole> umsRoles =adminRoleRelationDao.roleList(type);
        /*if (baseQuery.getAdminId()!=null){
            if (baseQuery.getAdminId()!=null){
                umsRolesForAmin=adminRoleRelationDao.roleForAdminList(baseQuery.getAdminId());
            }
        }
        for(UmsRole umsRole:umsRoles){
            for(UmsRole umsRole1:umsRolesForAmin){
                if (umsRole.getName().equals(umsRole1.getName())){
                    umsRole.setBeCheck(true);
                }
            }
        }*/

        return new PageInfo<>(umsRoles);
    }

    @Override
    public int addPermissionForRole(List<UmsRolePermissionRelation> permissionRelations) {
        adminRoleRelationDao.deletePermissionForRole(permissionRelations.get(0).getRoleId());
        if (permissionRelations.size()==1&&permissionRelations.get(0).getPermissionId()==null){
            return 0;
        }
        return adminRoleRelationDao.addPermissionForRole(permissionRelations);
    }

    @Override
    public Long isEnableRole(Long id, Long status) {
        if (adminRoleRelationDao.isEnableRole(id,status)==1){
            return status;
        }
        return -1L;
    }

    @Override
    public UmsRole addRole(UmsRole umsRole) {
        if (adminRoleRelationDao.addRole(umsRole)==1){
            return umsRole;
        }
        return null;
    }

    @Override
    public int deleteRoles(List<Long> ids) {
        return adminRoleRelationDao.deleteRoles(ids);
    }

    @Override
    public int modifyRole(UmsRole umsRole) {
        return adminRoleRelationDao.modifyRole(umsRole);
    }

    @Override
    public int addRolesForAdmin(List<UmsAdminRoleRelation> adminRoleRelation) {
        adminRoleRelationDao.deleteRoleForAdmin(adminRoleRelation.get(0).getAdminId());
        if (adminRoleRelation.size()==1&&adminRoleRelation.get(0).getRoleId()==null){
            return 0;
        }
        return adminRoleRelationDao.addRolesForAdmin(adminRoleRelation);
    }

    @Override
    public UmsAdmin updateAdminInfo(UmsAdmin umsAdmin) {
        if(umsAdmin.getPassword()!=null){
            umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        }
        int result=adminRoleRelationDao.updateAdminInfo(umsAdmin);
        if (result>0){
            return umsAdmin;
        }else {
            return null;
        }

    }

    @Override
    public int deleteUser(Long id) {
        return adminRoleRelationDao.deleteUser(id);
    }

//    public List<UmsPermission> getNode(Long pid){
//        List<UmsPermission> umsPermissions=adminRoleRelationDao.getAllPermission(pid);
//        for (UmsPermission umsPermission: umsPermissions) {
//            Long pid1=umsPermission.getId();
//            List<UmsPermission> umsPermissions1=getNode(pid1);
//            umsPermission.setUmsPermissions(umsPermissions1);
//        }
//        return umsPermissions;
//    }


}
