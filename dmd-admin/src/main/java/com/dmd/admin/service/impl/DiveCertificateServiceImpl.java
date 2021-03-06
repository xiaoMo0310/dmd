package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.DiveCertificateAdminMapper;
import com.dmd.admin.model.domain.CertificateAppBean;
import com.dmd.admin.service.DiveCertificateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateServiceImpl
 * @projectName dmd-masters
 * @description:
 * @date 2019/11/415:01
 */
@Service
public class DiveCertificateServiceImpl implements DiveCertificateService{

    @Autowired
    private DiveCertificateAdminMapper diveCertificateMapper;

    @Override
    public List<CertificateAppBean > queryDiveCertificate(Integer pageNum, Integer pageSize, CertificateAppBean certificateAppBean) {
        PageHelper.startPage(pageNum, pageSize);
        List<CertificateAppBean> certificateAppBeans = diveCertificateMapper.queryDiveCertificate(certificateAppBean);
        for (int i = 0; i < certificateAppBeans.size(); i++) {
            if(certificateAppBeans.get(i).getUserType() == 1){
                certificateAppBeans.get(i).setUserName(certificateAppBeans.get(i).getUserName());
            }else if(certificateAppBeans.get(i).getUserType() == 2){
                certificateAppBeans.get(i).setUserName(certificateAppBeans.get(i).getCoachName());
            }
        }
        return certificateAppBeans;
    }

    @Override
    public int updateDiveCertificateStatus(List<Long> ids) {
        return diveCertificateMapper.updateDiveCertificateStatus(ids);
    }

    @Override
    public int updateDiveCertificateStatusNoPass(List<Long> ids) {
        return diveCertificateMapper.updateDiveCertificateStatusNoPass(ids);
    }

    @Override
    public int updateCertificateStatusNoPass(CertificateAppBean certificateAppBean) {
        return diveCertificateMapper.updateCertificateStatusNoPass(certificateAppBean);
    }
}
