package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.OmsFashionable;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 分账表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-05
 */
@Mapper
@Component
public interface OmsFashionableMapper extends MyMapper<OmsFashionable> {

    /**
     * 根据分帐单编号查询信息
     * @param collectingNo
     * @return
     */
    OmsFashionable selectByCollectingNo(String collectingNo);

    List<OmsFashionable> selectFashionableList(BillingDetailDto billingDetailDto);

    int updateFashionableStatus(@Param("userName") String userName,@Param("userId") Long userId,@Param("collectingNo") String collectingNo,@Param("status") Integer status);
}
