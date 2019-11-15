package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.IntegralGiftSpeMapper;
import com.dmd.mall.mapper.IntegralGiftsMapper;
import com.dmd.mall.model.domain.IntegralGiftsBean;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;
import com.dmd.mall.service.IntegralGiftsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsServiceImpl
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/10/910:32
 */
@Service
public class IntegralGiftsServiceImpl implements IntegralGiftsService{

    @Autowired
    private IntegralGiftsMapper integralGiftsMapper;

    @Autowired
    private IntegralGiftSpeMapper integralGiftSpeMapper;

    @Override
    public List<IntegralGiftsBean> queryIntegralGifts() {
        return integralGiftsMapper.queryIntegralGifts();
    }

    @Override
    public List<IntegralGiftsBean> queryIntegralGiftsPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return integralGiftsMapper.queryIntegralGifts();
    }

    @Override
    public List<IntegralGiftsBean> queryIntegralGiftsById(Long id) {
        return integralGiftsMapper.queryIntegralGiftsById(id);
    }

    @Override
    public int addIntegralGifts(IntegralGiftsBean integralGiftsBean) {
        //添加时间为当前时间
        integralGiftsBean.setCreateTime(new Date());
        integralGiftsBean.setUpdateTime(null);
        return integralGiftsMapper.addIntegralGifts(integralGiftsBean);
    }

    @Override
    public IntegralGiftsBean findIntegralGiftsInfoById(Long id) {
        return integralGiftsMapper.findIntegralGiftsInfoById(id);
    }

    @Override
    public int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean) {
        integralGiftsBean.setUpdateTime(new Date());
        return integralGiftsMapper.updateIntegralGiftsById(integralGiftsBean);
    }

    @Override
    public int deleteIntegralGiftsById(Long id) {
        return integralGiftsMapper.deleteIntegralGiftsById(id);
    }

    @Override
    public List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id) {
        return integralGiftSpeMapper.queryIntegralGiftsSpeById(id);
    }


}
