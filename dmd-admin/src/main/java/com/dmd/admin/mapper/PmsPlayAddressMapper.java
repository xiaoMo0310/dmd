package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.PmsPlayAddress;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 潜水学习地址表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-06
 */
@Mapper
@Component
public interface PmsPlayAddressMapper extends MyMapper<PmsPlayAddress> {

    PmsPlayAddress selectIsDefaultAddress();

    PmsPlayAddress selectPlayAddressById(Long id);

    int deleteByIds(@Param("ids") List<Long> ids);
}
