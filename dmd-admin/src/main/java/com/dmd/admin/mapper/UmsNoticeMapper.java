package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsNotice;
import com.dmd.admin.model.dto.MessageListDto;
import com.dmd.admin.model.vo.NoticeListVo;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户通告表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
@Mapper
@Component
public interface UmsNoticeMapper extends MyMapper<UmsNotice> {

    List<NoticeListVo> findNoticeMessage(MessageListDto messageListDto);
}
