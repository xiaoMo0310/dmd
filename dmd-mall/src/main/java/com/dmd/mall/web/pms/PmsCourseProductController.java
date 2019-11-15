package com.dmd.mall.web.pms;


import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.dto.CourseProductDto;
import com.dmd.mall.model.vo.CertificateProductVo;
import com.dmd.mall.model.vo.DivingProductVo;
import com.dmd.mall.model.vo.PmsCourseListVo;
import com.dmd.mall.service.PmsCourseProductService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/certificateProduct/find")
    @ApiOperation(httpMethod = "GET", value = "查询学证产品的详细信息及教练信息,证书信息")
    @ApiImplicitParam(name ="certificateId", value = "证书id", dataType = "Long", paramType = "query")
    public Wrapper findCertificateProduct(@RequestParam("certificateId") Long certificateId) {
        CertificateProductVo certificateProductVo = pmsCourseProductService.findCertificateProduct(getLoginAuthDto(), certificateId);
        return WrapMapper.ok(certificateProductVo);
    }

    @PostMapping("/certificateProduct/find")
    @ApiOperation(httpMethod = "POST", value = "查询学证产品的详细信息")
    @ApiImplicitParam(name ="certificateProductDto", value = "查询学证产品需要的参数", dataType = "CertificateProductDto", paramType = "body")
    public Wrapper findCourseProductByIds(@RequestBody CertificateProductDto certificateProductDto) {
        PmsCourseProduct pmsCourseProduct = pmsCourseProductService.findCourseProductByIds(certificateProductDto);
        return WrapMapper.ok(pmsCourseProduct);
    }
}

