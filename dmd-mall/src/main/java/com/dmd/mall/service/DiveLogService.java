package com.dmd.mall.service;

import com.dmd.mall.model.domain.DiveLogAirbottleBean;
import com.dmd.mall.model.domain.DiveLogAndAirbottle;
import com.dmd.mall.model.domain.DiveLogBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogService
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1113:49
 */
public interface DiveLogService {
    List<DiveLogBean> queryDiveLogAll(Long userId,Integer pageNum,Integer pageSize);

    List<DiveLogBean> queryDiveLogById(Long id);

    List<DiveLogAirbottleBean> queryDiveLogAirbottleByDiveLogId(Integer pageNum,Integer pageSize,Long id);

    int addDiveLog(DiveLogAndAirbottle diveLogAndAirbottle);

    int updateLikePraise(Long id);

    int updateCancelPraise(Long id);

    Integer queryPraise(Long id);

    int updateDiveLogShare(Long id);

    Integer queryShare(Long id);

    int updateDiveLogDelflag(Long id);
}
