package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.CertificateAppBean;
import com.dmd.admin.model.domain.DiveCertificateBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateAdminMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/415:02
 */
public interface DiveCertificateAdminMapper {
    List<CertificateAppBean> queryDiveCertificate(CertificateAppBean diveCertificateBean);

    int updateDiveCertificateStatus(List<Long> ids);

    int updateDiveCertificateStatusNoPass(List<Long> ids);

    int updateCertificateStatusNoPass(CertificateAppBean certificateAppBean);
}
