package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsCertificate;
import com.dmd.mall.model.vo.PmsCertificateVo;

import java.util.List;

/**
 * <p>
 * 证书数据表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
public interface PmsCertificateService extends IService<PmsCertificate> {

    /**
     * 查询所有的证书信息
     * @return
     */
    List<PmsCertificateVo> selectCertificateList();

    /**
     * 根据id查询信息
     * @param certificateId
     * @return
     */
    PmsCertificateVo selectCertificateById(Long certificateId);

}
