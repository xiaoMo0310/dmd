package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsUserWallet;
import com.dmd.mall.model.vo.UmsWalletVo;

/**
 * <p>
 * 用户账户表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
public interface UmsUserWalletService extends IService<UmsUserWallet> {

    /**
     * 修改用户账户支付密码
     * @param loginAuthDto
     * @param confirmPwd
     * @param newPassword
     * @return
     */
    int editAccountPassword(LoginAuthDto loginAuthDto, String confirmPwd, String newPassword);

    /**
     * 查询当前用户钱包的所有的信息
     * @param loginAuthDto
     * @return
     */
    UmsWalletVo queryWalletMessage(LoginAuthDto loginAuthDto, BaseQuery baseQuery);
}
