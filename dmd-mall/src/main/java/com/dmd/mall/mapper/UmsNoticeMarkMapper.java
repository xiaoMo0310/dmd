package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsNoticeMark;
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
 * @since 2019-10-30
 */
@Mapper
@Component
public interface UmsNoticeMarkMapper extends MyMapper<UmsNoticeMark> {

    List<UmsNoticeMark> selectByUserId(@Param("userId") Long userId, @Param("userType") Integer userType);
}
