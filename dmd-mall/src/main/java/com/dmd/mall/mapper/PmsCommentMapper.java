package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsComment;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品评价表 Mapper 接口
 * </p>
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Mapper
@Component
public interface PmsCommentMapper extends MyMapper<PmsComment> {

    /**
     * 根据商品id查询评论的最新五条记录
     * @param productId
     * @return
     */
    List<PmsComment> selectCommentMessageByProductId(Long productId);
}
