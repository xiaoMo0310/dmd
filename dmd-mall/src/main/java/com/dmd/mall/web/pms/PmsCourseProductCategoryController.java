package com.dmd.mall.web.pms;

import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsCourseProductCategory;
import com.dmd.mall.service.PmsCourseProductCategoryService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductCategoryController", description = "潜水学证产品分类中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductCategoryController extends BaseController {

    @Autowired
    private PmsCourseProductCategoryService courseProductCategoryService;

    @PostMapping("/courseProduct/categoryList/{parentId}")
    @ApiOperation(httpMethod = "POST", value = "查询潜水学证商品的分类信息")
    @ApiImplicitParam(name ="parentId", value = "分类父id(0为一级分类)", dataType = "long", paramType = "path")
    public Wrapper getList(@PathVariable Long parentId) {
        List<PmsCourseProductCategory> courseProductCategory = courseProductCategoryService.getList(parentId);
        return WrapMapper.ok(courseProductCategory);
    }

}

