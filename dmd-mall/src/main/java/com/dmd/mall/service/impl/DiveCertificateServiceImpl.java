package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DiveCertificateMapper;
import com.dmd.mall.mapper.PmsCertificateMapper;
import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.PmsCertificate;
import com.dmd.mall.service.DiveCertificateServuce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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
public class DiveCertificateServiceImpl implements DiveCertificateServuce{

    @Autowired
    private DiveCertificateMapper diveCertificateMapper;
    @Autowired
    private PmsCertificateMapper pmsCertificateMapper;

    @Override
    public List<CertificateAppBean> queryDiveCertificate(Long userId) {
        //我上传的证书数量
        List<CertificateAppBean> certificateAppBeans = diveCertificateMapper.queryDiveCertificate(userId);
        //最大证书等级
        Integer max = diveCertificateMapper.queryCertificateMax(userId);
        //最小证书等级
        Integer min = diveCertificateMapper.queryCertificateMin(userId);
        //证书的数量
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.selectCertificateList();

        for (int i = 0 ; i < min; i++) {
            CertificateAppBean certificateAppBean = new CertificateAppBean();
            certificateAppBean.setCertificateId(pmsCertificates.get(i).getId().intValue());
            certificateAppBean.setCertificateName(pmsCertificates.get(i).getEnglishShorthand());
            certificateAppBean.setCertificateLevel(pmsCertificates.get(i).getCertificateLevel());
            certificateAppBeans.add(certificateAppBean);
        }



        for (int i = max ; i < pmsCertificates.size(); i++) {
            CertificateAppBean certificateAppBean = new CertificateAppBean();
            certificateAppBean.setCertificateId(pmsCertificates.get(i).getId().intValue());
            certificateAppBean.setCertificateName(pmsCertificates.get(i).getEnglishShorthand());
            certificateAppBean.setCertificateLevel(pmsCertificates.get(i).getCertificateLevel());
            certificateAppBeans.add(certificateAppBean);
        }



        /*for (int i = certificateAppBeans.size(); i < pmsCertificates.size(); i++) {
            CertificateAppBean certificateAppBean = new CertificateAppBean();
            certificateAppBean.setCertificateId(pmsCertificates.get(i).getId().intValue());
            certificateAppBean.setCertificateName(pmsCertificates.get(i).getEnglishShorthand());
            certificateAppBean.setCertificateLevel(pmsCertificates.get(i).getCertificateLevel());
            certificateAppBeans.add(certificateAppBean);
        }

        Collections.sort(certificateAppBeans, Comparator.comparing(CertificateAppBean::getCertificateLevel));*/
        return certificateAppBeans;
    }

    @Override
    public int addDiveCertificate(CertificateAppBean certificateAppBean) {
        //查询用户目前的最高证书等级
        //Integer identifierNum = diveCertificateMapper.selectCertificateId(certificateAppBean.getUserId());
        //Integer status = diveCertificateMapper.selectCertificateStatus(certificateAppBean.getUserId(),certificateAppBean.getCertificateId());
        CertificateAppBean certificateAppBean2 = diveCertificateMapper.selectCertificateByStatus(certificateAppBean.getUserId(),certificateAppBean.getCertificateId());
        Integer count = 0;
        /*//第一次上传


        if (identifierNum == null){
            identifierNum = 0;
        }
        if(status == null){
            status = 0;
        }
        if(certificateAppBean.getCertificateId() == 1&&certificateAppBean2==null){
            certificateAppBean.setCreateTime(new Date());
            certificateAppBean.setStatus(0);
            int count1 = diveCertificateMapper.addDiveCertificate(certificateAppBean);
            count = count1;

        }
        else if ( (certificateAppBean.getCertificateId() - identifierNum) == 1 && status != 0 && status != 2 ){
            certificateAppBean.setCreateTime(new Date());
            certificateAppBean.setStatus(0);
            int count2 = diveCertificateMapper.addDiveCertificate(certificateAppBean);
            count = count2;
        }*/
        //else{
            //用户重新上传去修改
        if (certificateAppBean2!=null){
            certificateAppBean.setId(certificateAppBean2.getId());
            int count3 = diveCertificateMapper.updateCertificate(certificateAppBean);
            count = count3;
        }else{
            certificateAppBean.setCreateTime(new Date());
            certificateAppBean.setStatus(0);
            int count2 = diveCertificateMapper.addDiveCertificate(certificateAppBean);
            count = count2;
        }
        /*}*/

        return count;
    }

    @Override
    public List<CertificateAppBean> queryUserCertificateList(Long userId) {
        List<CertificateAppBean> certificateAppBeans = diveCertificateMapper.queryUserCertificateList(userId);
        return certificateAppBeans;
    }
}
