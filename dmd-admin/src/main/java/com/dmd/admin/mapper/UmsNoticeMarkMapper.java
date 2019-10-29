package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsNoticeMark;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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

}
