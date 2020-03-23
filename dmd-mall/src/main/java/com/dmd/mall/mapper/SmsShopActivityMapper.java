package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.SmsShopActivity;
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
 * @since 2020-03-11
 */
@Mapper
@Component
public interface SmsShopActivityMapper extends MyMapper<SmsShopActivity> {

    List<SmsShopActivity> selectShopAvtivityList();
}
