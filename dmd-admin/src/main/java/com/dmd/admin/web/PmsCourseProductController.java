package com.dmd.admin.web;


import com.dmd.admin.model.domain.PmsCourseProduct;
import com.dmd.admin.model.dto.PmsCourseProductDto;
import com.dmd.admin.model.dto.PmsCourseProductListDto;
import com.dmd.admin.service.PmsCourseProductService;
import com.dmd.core.support.BaseController;
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
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductController extends BaseController {

    @Autowired
    private PmsCourseProductService pmsCourseProductService;

    @PostMapping("/courseProductList/find")
    @ApiOperation(httpMethod = "POST", value = "查询潜水学证的商品列表信息")
    @ApiImplicitParam(name ="courseProductListDto", value = "查询商品信息所需要的参数", paramType = "body", dataType = "PmsCourseProductListDto")
    public Wrapper findCourseProductList(@RequestBody PmsCourseProductListDto courseProductListDto) {
        PageInfo<PmsCourseProduct> pmsCourseProductPageInfo = pmsCourseProductService.findCourseProductList(courseProductListDto);
        return WrapMapper.ok(pmsCourseProductPageInfo);
    }

    @GetMapping("/courseProduct/find/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询商品的详细信息")
    @ApiImplicitParam(name ="id", value = "商品id", paramType = "path", dataType = "Long")
    public Wrapper findCourseProductMessage(@PathVariable Long id) {
        PmsCourseProduct courseProduct = pmsCourseProductService.selectByKey(id);
        return WrapMapper.ok(courseProduct);
    }

    @PostMapping("/approvalStatus/update")
    @ApiOperation(httpMethod = "POST", value = "修改商品的审核状态")
    @ApiImplicitParam(name ="courseProduct", value = "要修改的信息", paramType = "body", dataType = "PmsCourseProduct")
    public Wrapper updateProductApprovalStatus(@RequestBody PmsCourseProductDto courseProduct) {
        int result = pmsCourseProductService.updateProductApprovalStatus(getLoginAuthDto(), courseProduct);
        return handleResult(result);
    }
}
