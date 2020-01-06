package com.dmd.mall.web.pms;


import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.dto.CourseProductDto;
import com.dmd.mall.model.vo.CertificateProductVo;
import com.dmd.mall.model.vo.DivingProductVo;
import com.dmd.mall.model.vo.PmsCertificateVo;
import com.dmd.mall.model.vo.PmsCourseListVo;
import com.dmd.mall.service.PmsCourseProductService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程商品表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductController", description = "课程商品", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductController extends BaseController {

    @Autowired
    private PmsCourseProductService pmsCourseProductService;

    @PostMapping("/courseProduct/save")
    @ApiOperation(httpMethod = "POST", value = "添加或编辑潜水及学习产品的信息")
    @ApiImplicitParam(name ="courseProductDto", value = "课程产品的信息,修改需要提供id", dataType = "CourseProductDto", paramType = "body")
    public Wrapper saveCourseProductMessage(@RequestBody CourseProductDto courseProductDto) {
        logger.info("saveAttentionMessage - 编辑课程产品的信息. courseProduct={}", courseProductDto);
        int result = pmsCourseProductService.saveCourseProductMessage(getLoginAuthDto(), courseProductDto);
        return handleResult(result);
    }

    @GetMapping("/courseProduct")
    @ApiOperation(httpMethod = "GET", value = "查看商品的详细信息")
    @ApiImplicitParam(name ="id", value = "主键id", dataType = "long", paramType = "query")
    public Wrapper findCourseProductById(@RequestParam("id") Long id) {
        DivingProductVo divingProductVo = pmsCourseProductService.findCourseProductById(id);
        return WrapMapper.ok(divingProductVo);
    }

    @PostMapping("/divingProductList/find")
    @ApiOperation(httpMethod = "POST", value = "查询潜水商品的列表信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery", paramType = "body")
    public Wrapper findDivingProductMessage(@RequestBody BaseQuery baseQuery) {
        PageInfo<PmsCourseListVo> productList = pmsCourseProductService.findCourseProductListByType(baseQuery, 2);
        return WrapMapper.ok(productList);
    }

    @PostMapping("/divingProductList/findById")
    @ApiOperation(httpMethod = "POST", value = "查询卖家潜水商品的列表信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery", paramType = "body")
    public Wrapper findDivingProductList(@RequestBody BaseQuery baseQuery) {
        PageInfo<PmsCourseListVo> productList = pmsCourseProductService.findSellerCourseProductListByType(getLoginAuthDto(), baseQuery, 2);
        return WrapMapper.ok(productList);
    }

    @PostMapping("/certificateProduct/findById")
    @ApiOperation(httpMethod = "POST", value = "查询卖家所有的学证商品证书信息")
    public Wrapper findCertificateProduct() {
        List<PmsCertificateVo> pmsCertificates = pmsCourseProductService.findSellerCertificateMessage(getLoginAuthDto());
        return WrapMapper.ok(pmsCertificates);
    }

    @GetMapping("/certificateProduct/find")
    @ApiOperation(httpMethod = "GET", value = "查询学证产品的详细信息及教练信息,证书信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="certificateId", value = "证书id", dataType = "Long", paramType = "query"),
                        @ApiImplicitParam(name ="addressId", value = "地址id", dataType = "Long", paramType = "query")})
    @ApiImplicitParam(name ="certificateId", value = "证书id", dataType = "Long", paramType = "query")
    public Wrapper findCertificateProduct(@RequestParam("certificateId") Long certificateId, @RequestParam(value = "addressId", required = false) Long addressId) {
        CertificateProductVo certificateProductVo = pmsCourseProductService.findCertificateProduct(getLoginAuthDto(), certificateId, addressId);
        return WrapMapper.ok(certificateProductVo);
    }

    @PostMapping("/certificateProductDetail/find")
    @ApiOperation(httpMethod = "POST", value = "查询学证产品的详细信息")
    @ApiImplicitParam(name ="certificateProductDto", value = "查询学证产品需要的参数", dataType = "CertificateProductDto", paramType = "body")
    public Wrapper findCourseProductByIds(@RequestBody CertificateProductDto certificateProductDto) {
        JSONObject jsonObject = pmsCourseProductService.findCourseProductByIds(certificateProductDto);
        return WrapMapper.ok(jsonObject);
    }

    @GetMapping("courseProduct/settlement")
    @ApiOperation(httpMethod = "GET", value = "结算潜水学证商品")
    @ApiImplicitParam(name ="productId", value = "商品id", dataType = "Long", paramType = "query")
    public Wrapper settlementCourseProduct(@RequestParam("productId") Long productId) {
        PmsCourseListVo pmsCourseListVo = pmsCourseProductService.settlementCourseProduct(getLoginAuthDto(),productId);
        return WrapMapper.ok(pmsCourseListVo);
    }
}

