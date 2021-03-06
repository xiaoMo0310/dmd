package com.dmd.mall.service;

import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.PmsCertificate;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateServuce
 * @projectName dmd-masters
 * @description:
 * @date 2019/11/413:30
 */
public interface DiveCertificateServuce {
    List<CertificateAppBean> queryDiveCertificate(Long userId);

    int addDiveCertificate(CertificateAppBean certificateAppBean);

    List<PmsCertificate> queryDiveCertificateRank();

    List<CertificateAppBean> queryUserCertificateList(Long userId, String userType);

}
