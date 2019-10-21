package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.HomeSearchRecordBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: HomeSearchMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1818:18
 */
public interface HomeSearchMapper {
    void addHomeSearchRecord(@Param("userId") Long userId , @Param("content") String content,@Param("searchType") Integer searchType);

    List<HomeSearchRecordBean> queryHistory(Long userId);

    int deleteHistoryByUserid(Long userid);
}
