package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsUserWalletLog;
import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.vo.UmsWalletLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户账户日志表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@Mapper
@Component
public interface UmsUserWalletLogMapper extends MyMapper<UmsUserWalletLog> {

    List<UmsWalletLogVo> selectWalletLogMessage(Long userId);
}
