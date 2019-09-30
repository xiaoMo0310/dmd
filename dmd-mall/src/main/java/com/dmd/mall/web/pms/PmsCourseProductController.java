package com.dmd.mall.web.pms;


import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsCourseProduct;
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

import java.util.List;

/**
 * <p>
 * 课程商品表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/pms")
@Api(value = "课程产品", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,description = "课程产品",tags = "PmsCourseProductController")
public class PmsCourseProductController extends BaseController {

    @Autowired
    private PmsCourseProductService pmsCourseProductService;

    /**
     * 编辑课程产品的信息
     * @param courseProduct
     * @return
     */
    @PostMapping("/courseProduct/save")
    @ApiOperation(httpMethod = "POST", value = "编辑课程产品的信息")
    @ApiImplicitParam(name ="courseProduct", value = "课程产品的信息,修改需要提供id", dataType = "PmsCourseProduct")
    public Wrapper saveAttentionMessage(@RequestBody PmsCourseProduct courseProduct) {
        logger.info("saveAttentionMessage - 编辑课程产品的信息. courseProduct={}", courseProduct);
        int result = pmsCourseProductService.saveCourseProductMessage(getLoginAuthDto(), courseProduct);
        return handleResult(result);
    }

    /**
     * 分页查询所有课程产品的信息
     * @param
     * @return
     */
    @GetMapping("/courseProduct/findPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询所有课程产品的信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery")
    public Wrapper findAttentionMessage(@RequestBody BaseQuery baseQuery) {
        List<PageInfo> list = pmsCourseProductService.findcourseProduct(baseQuery, getLoginAuthDto());
        return WrapMapper.ok(list);
    }
}

