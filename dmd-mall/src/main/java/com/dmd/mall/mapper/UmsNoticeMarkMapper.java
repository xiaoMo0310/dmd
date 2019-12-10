package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.UmsNoticeMark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户通知标记表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Mapper
@Component
public interface UmsNoticeMarkMapper extends MyMapper<UmsNoticeMark> {

    List<UmsNoticeMark> selectByUserId(@Param("userId") Long userId, @Param("userType") String userType, @Param("messageType") Integer messageType);

    int updateIsRead(@Param("userId") Long userId, @Param("userName") String userName, @Param("noticeId") Long noticeId, @Param("userType") String userType, @Param("isRead") Integer isRead);

    UmsNoticeMark selectNoticeMarkMessageByNoticeId(@Param("noticeId") Long noticeId, @Param("userId") Long userId, @Param("userType") String userType);

    List<UmsNoticeMark> selectNoReadNotice(@Param("userId") Long userId, @Param("userType") String userType, @Param("isRead") Integer isRead);
}
