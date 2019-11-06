package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DiveCertificateBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/413:31
 */
public interface DiveCertificateMapper {
    List<DiveCertificateBean> queryDiveCertificate(Long userId);

    Integer queryUserId(Long userId);

    int addDiveCertificate(DiveCertificateBean diveCertificateBean);

    int updateDiveCertificate(DiveCertificateBean diveCertificateBean);

    Integer selectIdentifier(Long userId);
}
