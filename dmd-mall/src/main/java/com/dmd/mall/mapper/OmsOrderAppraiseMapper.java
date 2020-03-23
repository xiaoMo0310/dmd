package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsOrderAppraise;
import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.vo.ProductAppraiseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Mapper
@Component
public interface OmsOrderAppraiseMapper extends MyMapper<OmsOrderAppraise> {

    List<ProductAppraiseVo> selectAppraiseMessageByProductId(@Param( "productId" ) String productId, @Param( "level" ) String level);
}
