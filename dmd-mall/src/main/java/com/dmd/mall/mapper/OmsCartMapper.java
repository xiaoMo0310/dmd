package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsCart;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-12
 */
@Mapper
@Component
public interface OmsCartMapper extends MyMapper<OmsCart> {

}
