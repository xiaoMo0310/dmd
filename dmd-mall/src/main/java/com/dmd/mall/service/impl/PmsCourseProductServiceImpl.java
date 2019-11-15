package com.dmd.mall.service.impl;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.PmsCourseProductMapper;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.vo.*;
import com.dmd.mall.service.PmsCertificateService;
import com.dmd.mall.service.PmsCourseProductService;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.mall.service.UmsMemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程商品表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCourseProductServiceImpl extends BaseService<PmsCourseProduct> implements PmsCourseProductService {

    @Autowired
    private PmsCourseProductMapper pmsCourseProductMapper;
    @Autowired
    private PmsCertificateService certificateService;
    @Autowired
    private UmsCoachService umsCoachService;
    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public int saveCourseProductMessage(LoginAuthDto loginAuthDto, PmsCourseProduct courseProduct) {
        int resultInt;
        courseProduct.setUpdateInfo(loginAuthDto);
        if (courseProduct.isNew()) {
            courseProduct.setStatus(2);
            courseProduct.setApprovalStatus(1);
            resultInt = pmsCourseProductMapper.insertSelective(courseProduct);
        } else {
            resultInt = pmsCourseProductMapper.updateByPrimaryKeySelective(courseProduct);
        }
        return resultInt;
    }


    @Override
    public DivingProductVo findCourseProductById(Long id) {
        //查询商品信息
        PmsCourseProduct pmsCourseProduct = pmsCourseProductMapper.selectByPrimaryKey(id);
        if(pmsCourseProduct == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021004, pmsCourseProduct.getId());
        }
        DivingProductVo divingProductVo = new DivingProductVo();
        BeanUtils.copyProperties(pmsCourseProduct, divingProductVo);
        //查询教练信息
        UmsCoachVo umsCoachVo = umsCoachService.selectCoachMessage(pmsCourseProduct.getUserId());
        if(umsCoachVo == null){
            throw new UmsBizException(ErrorCodeEnum.UMS10011002, umsCoachVo.getId());
        }

        divingProductVo.setUmsCoachVo(umsCoachVo);
        return divingProductVo;
    }

    @Override
    public PageInfo<PmsCourseListVo> findCourseProductListByType(BaseQuery baseQuery, Integer type){
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<PmsCourseListVo> pmsCourseProductVos = pmsCourseProductMapper.selectCourseProductByType(type);
        if(CollectionUtils.isEmpty(pmsCourseProductVos)){
            throw new PmsBizException(ErrorCodeEnum.PMS10021003);
        }
        return new PageInfo<>(pmsCourseProductVos);
    }

    @Override
    public CertificateProductVo findCertificateProduct(LoginAuthDto loginAuthDto, Long certificateId) {
        CertificateProductVo certificateProductVo = new CertificateProductVo();
        //查询证书的信息
        PmsCertificateVo certificateVo = certificateService.selectCertificateById(certificateId);
        if(certificateVo == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021025, certificateId);
        }
        certificateProductVo.setPmsCertificateVo(certificateVo);
        //查询发布改证书的所有的教练id
        List<Long> coachIds = pmsCourseProductMapper.selectCoachIdByCertificateId(certificateId, 1);

        //查询当前登录人的信息
        UmsMember umsMember = umsMemberService.getById(loginAuthDto.getUserId());
        //查询教练的信息
        List<UmsCoachVo> coachVos = coachIds.stream().map(coachId -> {
            UmsCoachVo umsCoachVo = umsCoachService.selectCoachMessage(coachId);
            return umsCoachVo;
        }).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(coachVos)){
            throw new PmsBizException(ErrorCodeEnum.PMS10021003);
        }
        certificateProductVo.setUmsCoachVos(coachVos);
        //判断当前登录人和卖家的关系
        PmsCourseProduct pmsCourseProduct = null;
        for (UmsCoachVo coachVo : coachVos) {
            if(coachVo.getInvitationCode().equals(umsMember.getInvitationCode())){
                //查询该教练的商品信息
                try {
                    pmsCourseProduct = this.findCourseProductByCoachId(coachVo.getId());
                } catch (Exception e) {
                    throw new PmsBizException(ErrorCodeEnum.PMS10021004, coachVo.getId());
                }
            }else {
                //查询等级高的教练的商品信息
                UmsCoachVo maxCoach = coachVos.stream().filter(Objects::nonNull).max(Comparator.comparingInt(umsCoachVo -> Integer.valueOf(umsCoachVo.getCoachGrade()))).orElse(new UmsCoachVo());
                try {
                    pmsCourseProduct = this.findCourseProductByCoachId(maxCoach.getId());
                } catch (Exception e) {
                    throw new PmsBizException(ErrorCodeEnum.PMS10021004, coachVo.getId());
                }
            }
        }
        BeanUtils.copyProperties(pmsCourseProduct, certificateProductVo);
        return certificateProductVo;
    }

    @Override
    public PmsCourseProduct findCourseProductByIds(CertificateProductDto certificateProductDto){
        PmsCourseProduct pmsCourseProduct = pmsCourseProductMapper.selectCourseProductByIds(certificateProductDto);
        if(pmsCourseProduct == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021003);
        }
        return pmsCourseProduct;
    }

    @Override
    public PmsCourseProduct findCourseProductByCoachId(Long coachId){
        return pmsCourseProductMapper.selectByCoachId(coachId);
    }

    @Override
    public List<PmsCourseProduct> queryPowerNotesPage(Integer pageNum, Integer pageSize, Long userId, PmsCourseProduct pmsCourseProduct) {
        PageHelper.startPage(pageNum, pageSize);
        pmsCourseProduct.setUserId(userId);
        List<PmsCourseProduct> pmsCourseProducts = pmsCourseProductMapper.queryPowerNotesPage(pmsCourseProduct);
        //查询报名人数
        //pmsCourseProductMapper.selectNum();
        return pmsCourseProducts;
    }

    @Override
    public Integer queryPepleNum(Long id, Long userId) {
        return pmsCourseProductMapper.queryPepleNum(id,userId);
    }

}
