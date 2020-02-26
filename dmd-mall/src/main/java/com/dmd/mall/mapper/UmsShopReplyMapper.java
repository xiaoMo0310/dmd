package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsShopReply;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 教练店铺自动回复 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@Mapper
@Component
public interface UmsShopReplyMapper extends MyMapper<UmsShopReply> {

    List<UmsShopReply> selectReplyMessage(Long coachId);
}
