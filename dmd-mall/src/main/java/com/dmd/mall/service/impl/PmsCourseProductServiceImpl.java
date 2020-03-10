package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dmd.BeanUtils;
import com.dmd.PublicUtil;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.PmsCourseProductMapper;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.dto.CourseProductDto;
import com.dmd.mall.model.vo.*;
import com.dmd.mall.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
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
    @Autowired
    private PmsPlayAddressService playAddressService;

    @Override
    public int saveCourseProductMessage(LoginAuthDto loginAuthDto, CourseProductDto courseProductDto) {
        if(!loginAuthDto.getUserType().equals("coach")){
            throw new UmsBizException(ErrorCodeEnum.UMS10011023);
        }
        PmsCourseProduct courseProduct = new PmsCourseProduct();
        BeanUtils.copyProperties(courseProductDto, courseProduct);
        //判断是否有证书商品
        if(courseProductDto.getProductType() == 1 && courseProduct.isNew()){
            PmsCourseProduct pmsCourseProduct = pmsCourseProductMapper.selectByUserId(loginAuthDto.getUserId(), courseProductDto.getCertificateId(), courseProduct.getAddressId());
            if(!PublicUtil.isEmpty(pmsCourseProduct)){
                //查询证书信息
                PmsCertificateVo pmsCertificateVo = certificateService.selectCertificateById(pmsCourseProduct.getCertificateId());
                throw new PmsBizException(10021029, "您已发布" + pmsCertificateVo.getEnglishShorthand() + "证书,不能重复发布");
            }
        }
        //判断卖家是否有活动
        List<PmsCourseProduct> pmsCourseProducts = pmsCourseProductMapper.selectCheckActivity(courseProductDto.getStartTime(), courseProduct.getEndTime(),courseProduct.getId(), loginAuthDto.getUserId());
        if(!CollectionUtils.isEmpty(pmsCourseProducts)){
            throw new PmsBizException(ErrorCodeEnum.PMS10021029);
        }
        int resultInt;
        courseProduct.setUpdateInfo(loginAuthDto);
        courseProduct.setStatus(2);
        courseProduct.setApprovalStatus(1);
        courseProduct.setUserId(loginAuthDto.getUserId());
        //TODo 店铺信息待做
        if(!CollectionUtils.isEmpty(courseProductDto.getContentArrangements())){
            courseProduct.setContentArrangement(JSONArray.toJSONString(courseProductDto.getContentArrangements()));
        }
        if (courseProduct.isNew()) {
            if(courseProductDto.getProductType() == 1){
                courseProduct.setProductName("学证商品");
            }else if(courseProductDto.getProductType() == 2){
                courseProduct.setProductName("潜水商品");
            }
            resultInt = pmsCourseProductMapper.insertSelective(courseProduct);
        } else {
            courseProduct.setApprovalStatus(1);
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
        //下架与删除的商品不显示
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<PmsCourseListVo> pmsCourseProductVos = pmsCourseProductMapper.selectCourseProductByType(type);
        if(!CollectionUtils.isEmpty(pmsCourseProductVos)){
            for (PmsCourseListVo pmsCourseProductVo : pmsCourseProductVos) {
                pmsCourseProductVo.setImage(pmsCourseProductVo.getImage().split(",")[0]);
            }
        }
        return new PageInfo<>(pmsCourseProductVos);
    }

    @Override
    public CertificateProductVo findCertificateProduct(LoginAuthDto loginAuthDto, Long certificateId, Long addressId) {
        if(addressId == null){
            //统计地址下的所有的证书商品
            List<Map> maps = pmsCourseProductMapper.countCertificateProductNumByAddrrss(1, certificateId);
            if(CollectionUtils.isEmpty(maps)){
                throw new PmsBizException(ErrorCodeEnum.PMS10021030);
            }
            //查询默认地址
            PmsPlayAddress pmsPlayAddress = playAddressService.selectDefaultAddress();
            long defaultAddressCount = 0;
            if(pmsPlayAddress !=  null){
                defaultAddressCount = maps.stream().filter(map -> map.get("addressId") == pmsPlayAddress.getId() && (long) map.get("num") >0).count();
            }
            if(defaultAddressCount > 0){
                addressId = pmsPlayAddress.getId();
            }else {
                Map maxMap = maps.stream().max(Comparator.comparingLong(map -> (long) map.get("num"))).orElse(new HashMap(0));
                addressId = (Long) maxMap.get("addressId");
            }
        }
        CertificateProductVo certificateProductVo = new CertificateProductVo();
        //查询证书的信息
        PmsCertificateVo certificateVo = certificateService.selectCertificateById(certificateId);
        if(certificateVo == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021025, certificateId);
        }
        certificateProductVo.setPmsCertificateVo(certificateVo);
        //查询发布该证书的所有的教练id
        List<Long> coachIds = pmsCourseProductMapper.selectCoachIdByCertificateId(certificateId, addressId, 1);

        //查询当前登录人的信息
        UmsMember umsMember = umsMemberService.getById(loginAuthDto.getUserId());
        //查询教练的信息
        List<UmsCoachVo> coachVos = coachIds.stream().map(coachId -> {
            UmsCoachVo umsCoachVo = umsCoachService.selectCoachMessage(coachId);
            return umsCoachVo;
        }).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(coachVos)){
            throw new PmsBizException(ErrorCodeEnum.PMS10021030);
        }
        certificateProductVo.setUmsCoachVos(coachVos);
        //判断当前登录人和卖家的关系
        PmsCourseProduct pmsCourseProduct = null;
        UmsCoachVo coachVoA = null;
        Boolean flag = true;
        for (UmsCoachVo coachVo : coachVos) {
            if(coachVo.getInvitationCode().equals(umsMember.getInvitationCode())){
                //查询该教练的商品信息
                try {
                    pmsCourseProduct = this.findCourseProductByCoachId(coachVo.getId(), certificateId, addressId);
                    //将该教练的信息放到首位
                    coachVoA = coachVo;
                } catch (Exception e) {
                    throw new PmsBizException(ErrorCodeEnum.PMS10021004, coachVo.getId());
                }
                flag = false;
                continue;
            }
        }
        if(flag){
            //查询等级高的教练的商品信息
            UmsCoachVo maxCoach = coachVos.stream().filter(Objects::nonNull).max(Comparator.comparingInt(umsCoachVo -> Integer.valueOf(umsCoachVo.getCoachGrade()))).orElse(new UmsCoachVo());
            try {
                pmsCourseProduct = this.findCourseProductByCoachId(maxCoach.getId(), certificateId, addressId);
                coachVoA = maxCoach;
            } catch (Exception e) {
                throw new PmsBizException(ErrorCodeEnum.PMS10021004, maxCoach.getId());
            }
        }
        coachVos.remove(coachVoA);
        coachVos.add(0, coachVoA);
        if(pmsCourseProduct == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021030);
        }
        BeanUtils.copyProperties(pmsCourseProduct, certificateProductVo);
        return certificateProductVo;
    }

    @Override
    public JSONObject findCourseProductByIds(CertificateProductDto certificateProductDto){
        PmsCourseProduct pmsCourseProduct = pmsCourseProductMapper.selectCourseProductByIds(certificateProductDto);
        //获取证书信息
        PmsCertificate pmsCertificate = certificateService.selectByKey(certificateProductDto.getCertificateId());
        PmsCertificateVo pmsCertificateVo = new PmsCertificateVo();
        if(pmsCourseProduct == null || pmsCertificate == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021003);
        }
        BeanUtils.copyProperties(pmsCertificate, pmsCertificateVo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pmsCourseProduct", pmsCourseProduct);
        jsonObject.put("pmsCertificate", pmsCertificateVo);
        return jsonObject;
    }

    @Override
    public PmsCourseProduct findCourseProductByCoachId(Long coachId, Long certificateId, Long addressId){
        return pmsCourseProductMapper.selectByCoachId(coachId, certificateId, addressId);
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
        orderDetail.setProductTitle(product.getTitle());
        //封装商品sku数据
        Integer productType = product.getProductType();
        if(productType == 2){
            orderDetail.setProductType(2);
        }else if(productType == 1){
            orderDetail.setProductType(3);
        }
        Map mapA = new HashMap(0);
        mapA.put("key","开始时间");
        mapA.put("value", product.getStartTime());
        Map mapB = new HashMap(0);
        mapB.put("key","结束时间");
        mapB.put("value", product.getEndTime());
        Map mapC = new HashMap(0);
        mapC.put("key","最大人数限制");
        mapC.put("value", product.getNumberLimit());
        List<Map> arrayList = new ArrayList<>();
        arrayList.add(mapA);
        arrayList.add(mapB);
        arrayList.add(mapC);
        orderDetail.setProductAttr(JSONArray.toJSONStringWithDateFormat(arrayList, "yyyy-MM-dd HH:mm:ss"));
        orderDetail.setProductPrice(product.getPrice());
        return orderDetail;
    }

    @Override
    public PmsCourseListVo settlementCourseProduct(LoginAuthDto loginAuthDto, Long productId) {
        PmsCourseListVo pmsCourseListVo = pmsCourseProductMapper.selectCourseProductById(productId);
        if(pmsCourseListVo == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021003);
        }
        if(pmsCourseListVo.getStatus() == 4){
            throw new PmsBizException(ErrorCodeEnum.PMS10021027);
        }
        if(pmsCourseListVo.getStatus() == 5){
            throw new PmsBizException(ErrorCodeEnum.PMS10021033);
        }
        pmsCourseListVo.setImage(pmsCourseListVo.getImage().split(",")[0]);
        //查询当前用户可用积分数量
        UmsMember umsMember = umsMemberService.selectByLoginAuthDto(loginAuthDto);
        pmsCourseListVo.setAvailableIntegral(umsMember.getIntegration());
        return pmsCourseListVo;
    }

    @Override
    public long findCertificateProductNum(Integer productType, Long certificateId) {
        return pmsCourseProductMapper.countCertificateProductNum(productType, certificateId);
    }

    @Override
    public List<PmsCourseProduct> findCourseProductByStatus(Integer status) {
        return pmsCourseProductMapper.selectByStatus(status);
    }

    @Override
    public int updateCourseProductStatus(Long id, Integer status) {
        PmsCourseProduct courseProduct = new PmsCourseProduct();
        courseProduct.setId(id);
        courseProduct.setStatus(status);
        return pmsCourseProductMapper.updateByPrimaryKeySelective(courseProduct);
    }

    @Override
    public PageInfo<PmsCourseListVo> findSellerCourseProductListByType(LoginAuthDto loginAuthDto, BaseQuery baseQuery, Integer productType) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<PmsCourseListVo> pmsCourseProductVos = pmsCourseProductMapper.selectSellerCourseProductListByType(loginAuthDto.getUserId(), productType);
        if(!CollectionUtils.isEmpty(pmsCourseProductVos)){
            for (PmsCourseListVo pmsCourseProductVo : pmsCourseProductVos) {
                pmsCourseProductVo.setImage(pmsCourseProductVo.getImage().split(",")[0]);
            }
        }
        return new PageInfo<>(pmsCourseProductVos);
    }

    @Override
    public List<PmsCertificateVo> findSellerCertificateMessage(LoginAuthDto loginAuthDto) {
        List<PmsCourseProduct> pmsCourseProducts = pmsCourseProductMapper.selectProductByUserId(loginAuthDto.getUserId(), 1);
        return pmsCourseProducts.stream().map(pmsCourseProduct -> certificateService.selectCertificateById(pmsCourseProduct.getCertificateId())).collect(Collectors.toList());
    }

    @Override
    public Long countSellerProductNum(Long coachId) {
        return pmsCourseProductMapper.countSellerProductNum(coachId);
    }

    @Override
    public Integer queryPeopleNum(Long id, Long userId,Integer productType) {
        return pmsCourseProductMapper.queryPepleNum(id,userId,productType);
    }

    @Override
    public List<PowerNotesBean> queryPowerNotesCoachPage(Integer pageNum, Integer pageSize, Long userId, PmsCourseProduct pmsCourseProduct) {
        PageHelper.startPage(pageNum, pageSize);
        pmsCourseProduct.setUserId(userId);
        //教练的日程
        List<PmsCourseProduct> pmsCourseProducts = pmsCourseProductMapper.queryPowerNotesCoachPage(pmsCourseProduct);
        //新增到教练日程表
        //判断教练的日程是否已经新增：
        //日程表中该教练的所有产品id
        for (int i = 0; i < pmsCourseProducts.size(); i++) {
            //判断教练的这个日程是否已经新增
            Integer count = pmsCourseProductMapper.selectProductId(userId,pmsCourseProducts.get(i).getId());
            if(count == 0){
                //这条记录没有新增，去新增
                PowerNotesBean powerNotesBean = new PowerNotesBean();
                powerNotesBean.setImage(pmsCourseProducts.get(i).getImage());
                powerNotesBean.setTitle(pmsCourseProducts.get(i).getTitle());
                powerNotesBean.setProductName(pmsCourseProducts.get(i).getProductName());
                powerNotesBean.setStartTime(pmsCourseProducts.get(i).getStartTime());
                powerNotesBean.setEndTime(pmsCourseProducts.get(i).getEndTime());
                powerNotesBean.setUserId(userId);
                powerNotesBean.setProductType(pmsCourseProducts.get(i).getProductType());
                powerNotesBean.setProductId(pmsCourseProducts.get(i).getId());
                powerNotesBean.setLocation(pmsCourseProducts.get(i).getLocation());
                pmsCourseProductMapper.addProwerNotes(powerNotesBean);
            }
        }
        //教练的日程
        List<PowerNotesBean> powerNotesBean = pmsCourseProductMapper.selectPowerNotesPage(pmsCourseProduct);
        //日程状态  1==已完成 2==未完成
        for (int i = 0; i < powerNotesBean.size(); i++) {
            if (powerNotesBean.get(i).getEndTime().before(new Date())){
                powerNotesBean.get(i).setStatus(1);
            }else{
                powerNotesBean.get(i).setStatus(2);

            }
        }
        /*//查询报名人数
        for (int i = 0; i < pmsCourseProducts.size(); i++) {
            Integer integer = pmsCourseProductMapper.queryPepleNum(pmsCourseProducts.get(i).getId(), pmsCourseProducts.get(i).getUserId(), pmsCourseProducts.get(i).getProductType());
            pmsCourseProducts.get(i).setPeopleNum(integer);
        }*/

        return powerNotesBean;
    }

    @Override
    public List<PowerNotesBean> queryPowerNotesCoachToMonth(Long userId) {
        List<PowerNotesBean> powerNotesBeans = pmsCourseProductMapper.queryPowerNotesCoachToMonth(userId);
        //日程状态  1==已完成 2==未完成
        for (int i = 0; i < powerNotesBeans.size(); i++) {
            if (powerNotesBeans.get(i).getEndTime().before(new Date())){
                powerNotesBeans.get(i).setStatus(1);
            }else{
                powerNotesBeans.get(i).setStatus(2);

            }
        }
        return powerNotesBeans;
    }
}
