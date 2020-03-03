package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsShopNotice;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 教练店铺公告 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-27
 */
@Mapper
@Component
public interface UmsShopNoticeMapper extends MyMapper<UmsShopNotice> {

}
