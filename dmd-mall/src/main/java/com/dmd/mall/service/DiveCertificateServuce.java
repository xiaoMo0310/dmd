package com.dmd.mall.service;

import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.DiveCertificateBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateServuce
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/413:30
 */
public interface DiveCertificateServuce {
    List<CertificateAppBean> queryDiveCertificate(Long userId);

    int addDiveCertificate(CertificateAppBean certificateAppBean);
}
