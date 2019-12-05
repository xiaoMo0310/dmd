package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.DiveCertificateBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateMapper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/413:31
 */
public interface DiveCertificateMapper {
    List<CertificateAppBean> queryDiveCertificate(Long userId);

    Integer queryUserId(Long userId);

    int addDiveCertificate(CertificateAppBean certificateAppBean);

    int updateDiveCertificate(DiveCertificateBean diveCertificateBean);

    Integer selectIdentifier(Long userId);

    Integer selectCertificateId(Long userId);

    Integer selectCertificateStatus(@Param("userId") Long userId,@Param("identifier") Integer identifier);

    CertificateAppBean selectCertificateByStatus(@Param("userId")Long userId,@Param("certificateId") Integer certificateId);

    int updateCertificate(CertificateAppBean certificateAppBean);

    List<CertificateAppBean> queryUserCertificateList(Long userId);

    Integer queryCertificateMax(Long userId);

    Integer queryCertificateMin(Long userId);

    void addDiveCertificateAll(@Param("certificateLevel")String certificateLevel,@Param("userId")Long userId);
}
