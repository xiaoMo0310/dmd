package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.PmsCourseProductMapper;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.dto.CourseProductDto;
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

import java.util.*;
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
    public int saveCourseProductMessage(LoginAuthDto loginAuthDto, CourseProductDto courseProductDto) {
        if(!loginAuthDto.getUserType().equals("coach")){
            throw new UmsBizException(ErrorCodeEnum.UMS10011023);
        }
        PmsCourseProduct courseProduct = new PmsCourseProduct();
        BeanUtils.copyProperties(courseProductDto, courseProduct);
        //判断卖家是否有活动
        int count = pmsCourseProductMapper.selectCheckActivity(courseProductDto.getStartTime(), courseProduct.getEndTime(),courseProduct.getId());
        if(count > 0){
            throw new PmsBizException(ErrorCodeEnum.PMS10021029);
        }
        int resultInt;
        courseProduct.setUpdateInfo(loginAuthDto);
        courseProduct.setStatus(2);
        courseProduct.setApprovalStatus(1);
        if (courseProduct.isNew()) {
            resultInt = pmsCourseProductMapper.insertSelective(courseProduct);
        } else {
            //
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
    public OmsOrderItem createOrderItem(PmsCourseProduct product) {
        if (product.getApprovalStatus() == 1 || product.getApprovalStatus() == 3) {
            logger.error("商品未通过审核不能销售, productId={}", product.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021015, product.getId());
        }
        if(product.getStatus() == 2 || product.getStatus() == 3){
            logger.error("商品已下架或者删除, productId={}", product.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021017, product.getId());
        }
        //判断该商品是否已经到达时间节点
        if(System.currentTimeMillis() >= product.getStartTime().getTime()){
            logger.error("商品活动已经开始不能购买");
            throw new OmsBizException(ErrorCodeEnum.PMS10021027);
        }
        //判断商品的人数限制是否已经足够
        if(product.getStock() == 0){
            logger.error("商品已售完");
            throw new OmsBizException(ErrorCodeEnum.PMS10021016, product.getId());
        }
        OmsOrderItem orderDetail = new OmsOrderItem();
        //封装商品的信息
        orderDetail.setProductId(product.getId());
        orderDetail.setProductPic(product.getImage());
        orderDetail.setProductName(product.getProductName());
        orderDetail.setProductQuantity(1);
        orderDetail.setTotalPrice(product.getPrice());
        //封装商品sku数据
        Integer productType = product.getProductType();
        if(productType == 2){
            orderDetail.setProductType(2);
        }else if(productType == 1){
            orderDetail.setProductType(3);
        }
        Map map = new HashMap(0);
        map.put("startToEndTime", product.getStartTime().toString());
        map.put("endTime", product.getEndTime().toString());
        map.put("maxPerson", product.getNumberLimit());
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(map);
        orderDetail.setProductAttr(JSONArray.toJSONString(arrayList));
        orderDetail.setProductPrice(product.getPrice());
        return orderDetail;
    }
    @Override
    public Integer queryPepleNum(Long id, Long userId) {
        return pmsCourseProductMapper.queryPepleNum(id,userId);
    }

}
