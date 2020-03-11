package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.SmsShopActivity;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@Mapper
@Component
public interface SmsShopActivityMapper extends MyMapper<SmsShopActivity> {

    List<SmsShopActivity> selectActivityList(SmsShopActivity smsShopActivity);
}
