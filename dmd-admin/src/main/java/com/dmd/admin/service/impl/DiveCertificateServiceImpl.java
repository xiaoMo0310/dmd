package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.DiveCertificateAdminMapper;
import com.dmd.admin.model.domain.DiveCertificateBean;
import com.dmd.admin.service.DiveCertificateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/415:01
 */
@Service
public class DiveCertificateServiceImpl implements DiveCertificateService{

    @Autowired
    private DiveCertificateAdminMapper diveCertificateMapper;

    @Override
    public List<DiveCertificateBean> queryDiveCertificate(Integer pageNum, Integer pageSize, DiveCertificateBean diveCertificateBean) {
        PageHelper.startPage(pageNum, pageSize);
        return diveCertificateMapper.queryDiveCertificate(diveCertificateBean);
    }

    @Override
    public int updateDiveCertificateStatus(List<Long> ids) {
        return diveCertificateMapper.updateDiveCertificateStatus(ids);
    }

    @Override
    public int updateDiveCertificateStatusNoPass(List<Long> ids) {
        return diveCertificateMapper.updateDiveCertificateStatusNoPass(ids);
    }
}