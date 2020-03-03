package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.DiveCertificateMapper;
import com.dmd.mall.mapper.PmsCertificateMapper;
import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.PmsCertificate;
import com.dmd.mall.service.DiveCertificateServuce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ChenYanbing
 * @title: DiveCertificateServuceImpl
 * @projectName dmd-masters
 * @description:
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
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userType = loginAuthDto.getUserType();
        Integer userTypes = null;
        //用户登录
        if(userType.equals("member")){
            userTypes = 1;
        }
        //教练登陆
        if(userType.equals("coach")){
            userTypes = 2;
        }
        //我的证书
        List<CertificateAppBean> certificateAppBeans = diveCertificateMapper.queryDiveCertificate(userId,userTypes);
        //查询用户是否上传过证书
        Long num = diveCertificateMapper.selectCertificateBydUserId(userId,userTypes);
        if (num == 0 ){//用户无任何上传
            List<PmsCertificate> pmsCertificates = pmsCertificateMapper.selectCertificateList();
            for (int i = 0; i <pmsCertificates.size() ; i++) {
                CertificateAppBean certificateAppBean = new CertificateAppBean();
                certificateAppBean.setCertificateName(pmsCertificates.get(i).getEnglishShorthand());
                certificateAppBean.setCertificateLevel(pmsCertificates.get(i).getCertificateLevel());
                certificateAppBean.setCertificateId(Integer.valueOf(pmsCertificates.get(i).getCertificateLevel()));
                certificateAppBean.setUserType(userTypes);
                certificateAppBean.setStatus(3);
                certificateAppBeans.add(certificateAppBean);
            }
        }
       /* //证书的数量
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.selectCertificateList();*/

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
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userType = loginAuthDto.getUserType();
        Integer userTypes = null;
        //用户登录
        if(userType.equals("member")){
            userTypes = 1;
        }
        //教练登陆
        if(userType.equals("coach")){
            userTypes = 2;
        }
        //查询用户目前的最高证书等级
        //Integer identifierNum = diveCertificateMapper.selectCertificateId(certificateAppBean.getUserId());
        //Integer status = diveCertificateMapper.selectCertificateStatus(certificateAppBean.getUserId(),certificateAppBean.getCertificateId());
        CertificateAppBean certificateAppBean2 = diveCertificateMapper.selectCertificateByStatus(certificateAppBean.getUserId(),certificateAppBean.getCertificateId(),userTypes);
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
        //证书的数量
        if (certificateAppBean2!=null){
            certificateAppBean.setId(certificateAppBean2.getId());
            certificateAppBean.setUserType(userTypes);
            int count3 = diveCertificateMapper.updateCertificate(certificateAppBean);
            count = count3;
        }else{
            certificateAppBean.setCreateTime(new Date());
            certificateAppBean.setStatus(0);
            certificateAppBean.setUserType(userTypes);
            int count2 = diveCertificateMapper.addDiveCertificate(certificateAppBean);
            List<PmsCertificate> pmsCertificates = pmsCertificateMapper.queryCertificateList(certificateAppBean.getCertificateId());
            for (int i = 0; i < pmsCertificates.size(); i++) {
                diveCertificateMapper.addDiveCertificateAll(pmsCertificates.get(i).getCertificateLevel(),certificateAppBean.getUserId(),userTypes);
            }
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
