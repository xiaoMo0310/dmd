package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.PmsTagMapper;
import com.dmd.admin.model.domain.PmsTag;
import com.dmd.admin.service.PmsTagService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    public int saveOrUpdateTag(LoginAuthDto loginAuthDto,PmsTag pmsTag) {
        pmsTag.setUpdateInfo( loginAuthDto );
        int result = 0;
        if(pmsTag.isNew()){
            result = pmsTagMapper.insertSelective( pmsTag );
        }else {
            result = pmsTagMapper.updateByPrimaryKeySelective( pmsTag );
        }
        return result;
    }

    @Override
    public int deleteTag(List<Long> ids) {
        Example example = new Example( PmsTag.class );
        example.createCriteria().andIn( "id", ids );
        return pmsTagMapper.deleteByExample( example );
    }

    @Override
    public int updateShowStatus(LoginAuthDto loginAuthDto, List<Long> ids, Integer showStatus) {
        PmsTag updatePmsTab = new PmsTag();
        updatePmsTab.setShowStatus( showStatus );
        updatePmsTab.setUpdateInfo( loginAuthDto );
        Example example = new Example( PmsTag.class );
        example.createCriteria().andIn( "id", ids );
        return pmsTagMapper.updateByExampleSelective( updatePmsTab, example );
    }

    @Override
    public PmsTag findTagById(Long id) {
        return pmsTagMapper.selectByPrimaryKey( id );
    }

    @Override
    public PageInfo<PmsTag> findTagListByPage(PmsTag pmsTag) {
        PageHelper.startPage( pmsTag.getPageNum(), pmsTag.getPageSize() );
        List<PmsTag> pmsTaglist = pmsTagMapper.findTagListByPage(pmsTag);
        return new PageInfo<>( pmsTaglist );
    }
}
