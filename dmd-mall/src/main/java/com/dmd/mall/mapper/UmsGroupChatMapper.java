package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsGroupChat;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 教练群聊创建  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-30
 */
@Mapper
@Component
public interface UmsGroupChatMapper extends MyMapper<UmsGroupChat> {

    List<UmsGroupChat> selectCoachNeedGroupChart(String phone);
}
