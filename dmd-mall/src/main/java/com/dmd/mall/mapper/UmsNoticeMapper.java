package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户通告表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Mapper
@Component
public interface UmsNoticeMapper extends MyMapper<UmsNotice> {

    UmsNotice selectById(@Param("noticeId") Long noticeId, @Param("messageType") Integer messageType);

    List<UmsNotice> selectByType(@Param("type") Integer type, @Param("messageType") Integer messageType);

    Long countNoticeNum(@Param("userId")Long userId, @Param("userType") String userType, @Param("messageType") Integer messageType, @Param("jumpAddress") String jumpAddress);
}
