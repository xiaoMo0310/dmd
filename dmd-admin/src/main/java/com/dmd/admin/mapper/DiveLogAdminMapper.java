package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.DiveLogAirbottleBean;
import com.dmd.admin.model.domain.DiveLogBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogAdminMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/3114:30
 */
public interface DiveLogAdminMapper {
    List<DiveLogBean> queryDiveLogPage(DiveLogBean diveLogBean);

    List<DiveLogAirbottleBean> queryDiveLogAirbottleByDiveLogId(Long id);

    void updateDiveLogDelflag(List<Long> ids);

    void updateDiveLogAirbottleDelflag(List<Long> ids);
}
