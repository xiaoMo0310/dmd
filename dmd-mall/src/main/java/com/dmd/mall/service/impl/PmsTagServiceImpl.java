package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.PmsTag;
import com.dmd.mall.mapper.PmsTagMapper;
import com.dmd.mall.service.PmsTagService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsTagServiceImpl extends BaseService<PmsTag> implements PmsTagService {

    @Autowired
    private PmsTagMapper pmsTagMapper;

    @Override
    public List<PmsTag> findTagByType(Long tagType) {
        return pmsTagMapper.selectTagListByType(tagType);
    }
}
