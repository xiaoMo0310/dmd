package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsCoachShop;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 教练店铺店铺表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@Mapper
@Component
public interface UmsCoachShopMapper extends MyMapper<UmsCoachShop> {
    /**
     * 根据教练id查询店铺信息
     * @param coachId
     * @return
     */
    UmsCoachShop selectByCoachId(Long coachId);
}
