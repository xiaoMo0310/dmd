package com.dmd.mall.service;

import com.dmd.mall.model.domain.PmsTag;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-16
 */
public interface PmsTagService extends IService<PmsTag> {

    /**
     * 根据类型查询所有的标签
     * @param tagType
     * @return
     */
    List<PmsTag> findTagByType(Long tagType);
}
