package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsUserWallet;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户账户表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@Mapper
@Component
public interface UmsUserWalletMapper extends MyMapper<UmsUserWallet> {

}
