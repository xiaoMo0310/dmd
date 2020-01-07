package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.PmsCertificateMapper;
import com.dmd.admin.model.domain.PmsCertificate;
import com.dmd.admin.model.dto.PmsCertificateDto;
import com.dmd.admin.service.PmsCertificateService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 证书数据表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class
PmsCertificateServiceImpl extends BaseService<PmsCertificate> implements PmsCertificateService {

    @Autowired
    private PmsCertificateMapper pmsCertificateMapper;

    @Override
    public PageInfo<PmsCertificate> findCertificateList(PmsCertificateDto certificateDto) {
        PageHelper.startPage(certificateDto.getPageNum(), certificateDto.getPageSize(), certificateDto.getOrderBy());
        List<PmsCertificate> certificates = pmsCertificateMapper.selectCertificateList(certificateDto);
        return new PageInfo<>(certificates);
    }

    @Override
    public int updateCertificateStatus(LoginAuthDto loginAuthDto, PmsCertificate pmsCertificate) {
        int resultInt;
        pmsCertificate.setUpdateInfo(loginAuthDto);
        if (pmsCertificate.isNew()) {
            pmsCertificate.setStatus(1);
            resultInt = pmsCertificateMapper.insertSelective(pmsCertificate);
        } else {
            resultInt = pmsCertificateMapper.updateByPrimaryKeySelective(pmsCertificate);
        }
        return resultInt;
    }

    @Override
    public PmsCertificate findCertificateById(Long id) {
        return pmsCertificateMapper.selectByPrimaryKey(id);
    }

    @Override
    public int selectCertificateCount() {
        return pmsCertificateMapper.selectCertificateCount();
    }

    @Override
    public PmsCertificate findCertificateAndPageById(Long id, Integer pageSize) {
        PmsCertificate certificate = this.findCertificateById(id);
        //查询证书信息位于第几页
        Long beforeNum = pmsCertificateMapper.countBeforeCertificate(Integer.valueOf(certificate.getCertificateLevel()));
        Long pageNum = (beforeNum/pageSize) + 1;
        certificate.setPageNum(Integer.valueOf(pageNum + ""));
        return certificate;
    }

}
