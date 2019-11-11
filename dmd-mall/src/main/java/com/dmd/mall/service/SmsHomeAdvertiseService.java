package com.dmd.mall.service;

import com.dmd.mall.model.domain.SmsHomeAdvertise;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    /**
     * 根据显示位置查询图片
     * @param type
     * @return
     */
    List<SmsHomeAdvertise> selectAdvertisePicList(Integer type);
}
