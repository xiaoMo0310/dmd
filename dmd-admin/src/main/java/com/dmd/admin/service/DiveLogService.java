package com.dmd.admin.service;

import com.dmd.admin.model.domain.DiveLogAirbottleBean;
import com.dmd.admin.model.domain.DiveLogBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/3114:29
 */
public interface DiveLogService {
    List<DiveLogBean> queryDiveLogPage(Integer pageNum, Integer pageSize, DiveLogBean diveLogBean);

    List<DiveLogAirbottleBean> queryDiveLogAirbottleByDiveLogId(Long id);

    int updateDiveLogDelflag(List<Long> ids);
}
