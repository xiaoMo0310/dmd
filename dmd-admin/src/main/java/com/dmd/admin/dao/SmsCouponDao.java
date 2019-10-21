package com.dmd.admin.dao;

import com.dmd.admin.model.dto.SmsCouponParam;
import org.apache.ibatis.annotations.Param;

/**
 * 优惠券管理自定义查询Dao
 * Created by macro on 2018/8/29.
 */
public interface SmsCouponDao {
    SmsCouponParam getItem(@Param("id") Long id);
}
