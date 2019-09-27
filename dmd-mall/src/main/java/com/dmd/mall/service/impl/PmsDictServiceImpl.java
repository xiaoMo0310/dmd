package com.dmd.mall.service.impl;

import com.dmd.PublicUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.PmsDictMapper;
import com.dmd.mall.model.domain.PmsDict;
import com.dmd.mall.model.vo.PmsDictVo;
import com.dmd.mall.service.PmsDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsDictServiceImpl extends BaseService<PmsDict> implements PmsDictService {

    @Autowired
    private PmsDictMapper pmsDictMapper;

    /**
     * 查询所有的加工类型
     * @return
     */
    @Override
    public List<PmsDictVo> findAllProcessingType(String key) {
        PmsDict queryMdcDict = new PmsDict();
        queryMdcDict.setDictKey(key);
        queryMdcDict.setStatus(1);
        queryMdcDict = pmsDictMapper.selectOne(queryMdcDict);

        PmsDict pmsDict = new PmsDict();
        pmsDict.setPid(queryMdcDict.getId());
        pmsDict.setStatus(1);
        List<PmsDict> mdcDicts = pmsDictMapper.select(pmsDict);
        List<PmsDictVo> list = new ArrayList<>();
        for (PmsDict dict : mdcDicts) {
            PmsDictVo pmsDictVo = new PmsDictVo();
            BeanUtils.copyProperties(dict,pmsDictVo);
            list.add(pmsDictVo);
        }
        return list;
    }

    @Override
    public PmsDictVo queryValueByKey(String parentKey, String key){
        PmsDict queryMdcDict = new PmsDict();
        queryMdcDict.setDictKey(parentKey);
        queryMdcDict.setStatus(1);
        queryMdcDict = pmsDictMapper.selectOne(queryMdcDict);

        PmsDict mdcDict = new PmsDict();
        mdcDict.setPid(queryMdcDict.getId());
        mdcDict.setDictKey(key);
        mdcDict.setStatus(1);
        mdcDict = pmsDictMapper.selectOne(mdcDict);
        if(mdcDict == null){
            throw new UmsBizException("没有查询到字典表的数据");
        }
        PmsDictVo mdcDictVo = new PmsDictVo();
        BeanUtils.copyProperties(mdcDict,mdcDictVo);
        return mdcDictVo;
    }

    @Override
    public int saveMdcDict(PmsDict pmsDict, LoginAuthDto loginAuthDto){
        Long pid = pmsDict.getPid();
        pmsDict.setUpdateInfo(loginAuthDto);
        PmsDict parentMenu = pmsDictMapper.selectByPrimaryKey(pid);
        if (PublicUtil.isEmpty(parentMenu)) {
            throw new UmsBizException("上级数据字典不存在");
        }
        int result = 0;
        if (pmsDict.isNew()) {
            PmsDict updateMenu = new PmsDict();
            updateMenu.setId(pid);
            result = pmsDictMapper.insertSelective(pmsDict);
        } else {
            result = pmsDictMapper.updateByPrimaryKeySelective(pmsDict);
        }
        return result;
    }
}
