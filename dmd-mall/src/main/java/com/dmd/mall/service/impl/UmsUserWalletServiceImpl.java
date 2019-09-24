package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsUserWalletMapper;
import com.dmd.mall.model.domain.UmsUserWallet;
import com.dmd.mall.service.UmsUserWalletService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsUserWalletServiceImpl extends BaseService<UmsUserWallet> implements UmsUserWalletService {

    @Autowired
    private UmsUserWalletMapper umsUserWalletMapper;

    @Override
    public int editAccountPassword(LoginAuthDto loginAuthDto, String confirmPwd, String newPassword) {
        Preconditions.checkArgument(!StringUtils.isEmpty(newPassword), ErrorCodeEnum.UMS10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(confirmPwd), ErrorCodeEnum.UMS10011009.msg());
        Preconditions.checkArgument(newPassword.equals(confirmPwd), "两次输入密码不一致");
        UmsUserWallet userWallet = new UmsUserWallet();
        userWallet.setUserId(loginAuthDto.getUserId());
        userWallet = umsUserWalletMapper.selectOne(userWallet);
        userWallet.setWalletPassword(newPassword);
        userWallet.setUpdateInfo(loginAuthDto);
        return umsUserWalletMapper.updateByPrimaryKeySelective(userWallet);
    }
}
