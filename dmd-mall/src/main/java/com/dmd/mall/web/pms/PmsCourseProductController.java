package com.dmd.mall.web.pms;


import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.vo.PmsCourseProductVo;
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
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductController", description = "课程商品", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductController extends BaseController {

    @Autowired
    private PmsCourseProductService pmsCourseProductService;


    @PostMapping("/courseProduct/save")
    @ApiOperation(httpMethod = "POST", value = "编辑课程产品的信息")
    @ApiImplicitParam(name ="courseProduct", value = "课程产品的信息,修改需要提供id", dataType = "PmsCourseProduct")
    public Wrapper saveAttentionMessage(@RequestBody PmsCourseProduct courseProduct) {
        logger.info("saveAttentionMessage - 编辑课程产品的信息. courseProduct={}", courseProduct);
        int result = pmsCourseProductService.saveCourseProductMessage(getLoginAuthDto(), courseProduct);
        return handleResult(result);
    }


    @PostMapping("/courseProduct/findPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询所有课程产品的列表信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery")
    public Wrapper findAttentionMessage(@RequestBody BaseQuery baseQuery) {
        List<PageInfo> list = pmsCourseProductService.findcourseProduct(baseQuery, getLoginAuthDto());
        return WrapMapper.ok(list);
    }


    @GetMapping("/courseProduct/{id}")
    @ApiOperation(httpMethod = "GET", value = "分页查询所有课程产品的列表信息")
    @ApiImplicitParam(name ="id", value = "主键id", dataType = "long")
    public Wrapper findAttentionMessage(@PathVariable Long id) {
        PmsCourseProductVo courseProductVo = pmsCourseProductService.findcourseProductById(getLoginAuthDto(), id);
        return WrapMapper.ok(courseProductVo);
    }
}

