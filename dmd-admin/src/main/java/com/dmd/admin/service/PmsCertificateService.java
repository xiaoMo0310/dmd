package com.dmd.admin.service;

import com.dmd.admin.model.domain.PmsCertificate;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

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
     * 分页查询数据
     * @param baseQuery
     * @return
     */
    PageInfo<PmsCertificate> findCertificateList(BaseQuery baseQuery);

    /**
     * 添加或修改证书信息
     * @param loginAuthDto
     * @param pmsCertificate
     * @return
     */
    int updateCertificateStatus(LoginAuthDto loginAuthDto, PmsCertificate pmsCertificate);
}