package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsPlayAddress;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 潜水学习地址表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-08
 */
@Mapper
@Component
public interface PmsPlayAddressMapper extends MyMapper<PmsPlayAddress> {

    List<PmsPlayAddress> selectAllPlayAddress();

    PmsPlayAddress selectDefaultAddress();
}
