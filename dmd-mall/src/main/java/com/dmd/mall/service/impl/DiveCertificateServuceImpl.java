package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DiveCertificateMapper;
import com.dmd.mall.model.domain.DiveCertificateBean;
import com.dmd.mall.service.DiveCertificateServuce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateServuceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/413:30
 */
@Service
public class DiveCertificateServuceImpl implements DiveCertificateServuce{

    @Autowired
    private DiveCertificateMapper diveCertificateMapper;

    @Override
    public List<DiveCertificateBean> queryDiveCertificate(Long userId) {
        return diveCertificateMapper.queryDiveCertificate(userId);
    }

    @Override
    public int addDiveCertificate(Long userId, DiveCertificateBean diveCertificateBean) {
        //判断userid在表中字段是否存在,不存在则第一次上传进行新增,存在则进行修改操作
        //查询的结果=0 就不存在 >0 就存在
        Integer bool = diveCertificateMapper.queryUserId(userId);
        int count1 = 0;
        if (bool == 0){
            diveCertificateBean.setUserId(userId);
            diveCertificateBean.setCreateTime(new Date());
            diveCertificateBean.setStatus(0);
            int count = diveCertificateMapper.addDiveCertificate(diveCertificateBean);
            count1 = count;
        }if (bool > 0){
            diveCertificateBean.setUserId(userId);
            diveCertificateBean.setCreateTime(new Date());
            diveCertificateBean.setStatus(0);
            int count = diveCertificateMapper.updateDiveCertificate(diveCertificateBean);
            count1 = count;
        }
        return count1;
    }
}
