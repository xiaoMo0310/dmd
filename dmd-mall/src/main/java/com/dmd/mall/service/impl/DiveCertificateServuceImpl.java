package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DiveCertificateMapper;
import com.dmd.mall.mapper.PmsCertificateMapper;
import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.DiveCertificateBean;
import com.dmd.mall.model.domain.PmsCertificate;
import com.dmd.mall.service.DiveCertificateServuce;
import com.dmd.mall.service.PmsCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private PmsCertificateMapper pmsCertificateMapper;

    @Override
    public List<CertificateAppBean> queryDiveCertificate(Long userId) {
        List<CertificateAppBean> certificateAppBeans = diveCertificateMapper.queryDiveCertificate(userId);
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.selectCertificateList();
        for (int i = certificateAppBeans.size(); i < pmsCertificates.size(); i++) {
            CertificateAppBean certificateAppBean = new CertificateAppBean();
            certificateAppBean.setCertificateId(pmsCertificates.get(i).getId().intValue());
            certificateAppBean.setCertificateName(pmsCertificates.get(i).getEnglishShorthand());
            certificateAppBeans.add(certificateAppBean);
        }

        System.out.println(certificateAppBeans);
        return certificateAppBeans;
    }

    @Override
    public int addDiveCertificate(CertificateAppBean certificateAppBean) {
        //查询用户目前的最高证书等级
        Integer identifierNum = diveCertificateMapper.selectCertificateId(certificateAppBean.getUserId());
        Integer stauts2 = diveCertificateMapper.selectCertificateByStatus(certificateAppBean.getUserId(),certificateAppBean.getCertificateId());
        Integer status = diveCertificateMapper.selectCertificateStatus(certificateAppBean.getUserId(),certificateAppBean.getCertificateId());
        //第一次上传
        Integer count = null;

        if (identifierNum == null){
            identifierNum = 0;
        }
        if(status == null){
            status = 0;
        }
        if(certificateAppBean.getCertificateId() == 1){
            certificateAppBean.setCreateTime(new Date());
            certificateAppBean.setStatus(0);
            int count1 = diveCertificateMapper.addDiveCertificate(certificateAppBean);
            count = count1;

        }else if(stauts2 == 2){
            //用户重新上传去修改
            int count3 = diveCertificateMapper.updateCertificate(certificateAppBean);
            count = count3;
        }

        else if ( (certificateAppBean.getCertificateId() - identifierNum) == 1 && status != 0 && status != 2 ){
            certificateAppBean.setCreateTime(new Date());
            certificateAppBean.setStatus(0);
            int count2 = diveCertificateMapper.addDiveCertificate(certificateAppBean);
            count = count2;
        }else{
            count = 0;
        }

        return count;
    }
}
