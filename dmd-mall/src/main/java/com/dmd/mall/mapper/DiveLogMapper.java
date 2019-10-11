package com.dmd.mall.mapper;

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
}
