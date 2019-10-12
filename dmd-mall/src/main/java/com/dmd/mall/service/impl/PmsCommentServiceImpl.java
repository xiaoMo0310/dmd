package com.dmd.mall.service.impl;

import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsCommentMapper;
import com.dmd.mall.model.domain.PmsComment;
import com.dmd.mall.service.PmsCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCommentServiceImpl extends BaseService<PmsComment> implements PmsCommentService {

    @Autowired
    private PmsCommentMapper pmsCommentMapper;

    @Override
    public PageInfo<PmsComment> findCommentMessge(BaseQuery baseQuery, Long productId) {
        Example example =new Example(PmsComment.class);
        example.setOrderByClause("update_time desc");
        example.createCriteria().andEqualTo("productId",productId);
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<PmsComment> pmsComments = pmsCommentMapper.selectByExample(example);
        return new PageInfo<PmsComment>(pmsComments);
    }
}
