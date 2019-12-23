package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsNoticeMark;
import com.dmd.admin.model.vo.NoticeMarkVo;
import com.dmd.core.mybatis.MyMapper;
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
 * @since 2019-10-29
 */
@Mapper
@Component
public interface UmsNoticeMarkMapper extends MyMapper<UmsNoticeMark> {

    List<NoticeMarkVo> selectByNoticeId(@Param("noticeId") Long noticeId, @Param("userType") String userType);

    List<NoticeMarkVo> selectCoachMessageByNoticeId(@Param("noticeId") Long noticeId, @Param("userType") String userType);
}
