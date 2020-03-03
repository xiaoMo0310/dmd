package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsShopNotice;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    List<UmsShopNotice> selectByList(UmsShopNotice umsShopNotice);
}
