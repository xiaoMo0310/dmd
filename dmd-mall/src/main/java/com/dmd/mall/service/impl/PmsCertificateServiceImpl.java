package com.dmd.mall.service.impl;

import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsCertificateMapper;
import com.dmd.mall.model.domain.PmsCertificate;
import com.dmd.mall.model.vo.PmsCertificateVo;
import com.dmd.mall.service.PmsCertificateService;
import com.dmd.mall.service.PmsCourseProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private PmsCourseProductService courseProductService;

    @Override
    public List<PmsCertificateVo> selectCertificateList() {
        List<PmsCertificate> pmsCertificates = pmsCertificateMapper.selectCertificateList();
        return pmsCertificates.stream().map(pmsCertificate -> {
            PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
            BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
            //查询是否有商品信息
            long count = courseProductService.findCertificateProductNum(1, pmsCertificate.getId());
            pmsCertificateVo.setProductNum(count);
            return pmsCertificateVo;
        }).collect(Collectors.toList());
    }

    @Override
    public PmsCertificateVo selectCertificateById(Long certificateId) {
        PmsCertificate pmsCertificate = pmsCertificateMapper.selectByPrimaryKey(certificateId);
        PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
        if(pmsCertificateVo != null){
            BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
        }
        return pmsCertificateVo;
    }

}
