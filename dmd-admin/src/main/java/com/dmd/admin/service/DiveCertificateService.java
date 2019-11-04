package com.dmd.admin.service;

import com.dmd.admin.model.domain.DiveCertificateBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateService
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/415:00
 */
public interface DiveCertificateService {
    List<DiveCertificateBean> queryDiveCertificate(Integer pageNum, Integer pageSize, DiveCertificateBean diveCertificateBean);

    int updateDiveCertificateStatus(List<Long> ids);

    int updateDiveCertificateStatusNoPass(List<Long> ids);
}
