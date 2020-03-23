package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsProductTagMapper;
import com.dmd.mall.model.domain.PmsProductTag;
import com.dmd.mall.service.PmsProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品标签关系表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsProductTagServiceImpl extends BaseService<PmsProductTag> implements PmsProductTagService {

    @Autowired
    private PmsProductTagMapper pmsProductTagMapper;

    @Override
    public int batchSaveProductTag(Long productId, List<Long> tagIds) {
        List<PmsProductTag> pmsProductTags = tagIds.stream().map( tagId -> {
            PmsProductTag pmsProductTag = new PmsProductTag();
            pmsProductTag.setProductId( productId );
            pmsProductTag.setTagId( tagId );
            return pmsProductTag;
        } ).collect( Collectors.toList() );
        return pmsProductTagMapper.insertList( pmsProductTags );
    }

    @Override
    public int batchUpdateProductTag(Long productId, List<Long> tagIds) {
        pmsProductTagMapper.deleteByProductId( productId );
        return batchSaveProductTag( productId, tagIds );
    }
}
