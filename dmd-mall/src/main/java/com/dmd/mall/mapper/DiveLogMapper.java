package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DiveLogAirbottleBean;
import com.dmd.mall.model.domain.DiveLogBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1113:50
 */
public interface DiveLogMapper {
    void addrCommentNum(Long forDiveLogId);

    void reduceCommentNum(Long forDiveLogId);

    List<DiveLogBean> queryDiveLogAll(Long userId);

    List<DiveLogBean> queryDiveLogById(Long id);

    List<DiveLogAirbottleBean> queryDiveLogAirbottleByDiveLogId(Long id);

    void addDiveLog(DiveLogBean diveLogBean);

    int addDiveLogAirbottle(@Param("diveLogAirbottleList")List<DiveLogAirbottleBean> diveLogAirbottleList);

    int updateLikePraise(Long id);

    int updateCancelPraise(Long id);

    Integer queryPraise(Long id);

    int updateDiveLogShare(Long id);

    Integer queryShare(Long id);
}
