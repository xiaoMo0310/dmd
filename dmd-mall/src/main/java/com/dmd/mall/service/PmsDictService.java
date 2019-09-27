package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsDict;
import com.dmd.mall.model.vo.PmsDictVo;

import java.util.List;

/**
 * <p>
 * 字典表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-27
 */
public interface PmsDictService extends IService<PmsDict> {

    /**
     * 根据父级字典key获取子级
     * @param key
     * @return
     */
    List<PmsDictVo> findAllProcessingType(String key);

    /**
     * 根据父级字典key和当前key 获取当前Value
     * @param parentKey
     * @param key
     * @return
     */
    PmsDictVo queryValueByKey(String parentKey, String key);

    /**
     * 编辑字典.
     * @param mdcDict      the mdc dict
     * @param loginAuthDto the login auth dto
     */
    int saveMdcDict(PmsDict mdcDict, LoginAuthDto loginAuthDto);
}
