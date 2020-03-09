package com.dmd.admin.web;

import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.model.domain.PmsCourseProductCategory;
import com.dmd.admin.model.dto.PmsCourseProductCategoryDto;
import com.dmd.admin.service.PmsCourseProductCategoryService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductCategoryController", description = "潜水学证产品分类中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductCategoryController extends BaseController {

    @Autowired
    private PmsCourseProductCategoryService courseProductCategoryService;

    @PostMapping("/courseProduct/category/create")
    @ApiOperation(httpMethod = "POST", value = "添加潜水学证产品分类")
    @ApiImplicitParam(name ="courseProductCategoryDto", value = "添加分类数据", dataType = "PmsCourseProductCategoryDto", paramType = "body")
    public Wrapper create(@Validated @RequestBody PmsCourseProductCategoryDto courseProductCategoryDto,
                          BindingResult result) {
        int count = courseProductCategoryService.createCategory(getLoginAuthDto(), courseProductCategoryDto);
        return handleResult( count );
    }

    @PostMapping("/courseProduct/category/update/{id}")
    @ApiOperation(httpMethod = "POST", value = "修改商品分类")
    @ApiImplicitParams( {@ApiImplicitParam(name ="id", value = "分类id", dataType = "long", paramType = "path"),
                        @ApiImplicitParam(name ="courseProductCategoryDto", value = "添加分类数据", dataType = "PmsCourseProductCategoryDto", paramType = "body")} )
    public Wrapper update(@PathVariable Long id,
                               @Validated
                               @RequestBody PmsCourseProductCategoryDto courseProductCategoryDto,
                               BindingResult result) {
        int count = courseProductCategoryService.updateCategory(getLoginAuthDto(), id, courseProductCategoryDto);
         return handleResult( count );
    }


    @PostMapping("/courseProduct/categoryList/{parentId}")
    @ApiOperation(httpMethod = "POST", value = "分页查询潜水学证商品的信息")
    @ApiImplicitParams( {@ApiImplicitParam(name ="parentId", value = "分类父id", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery", paramType = "body")} )
    public Wrapper getList(@PathVariable Long parentId,
                           @RequestBody BaseQuery baseQuery) {
        JSONObject object = courseProductCategoryService.getList(parentId, baseQuery);
        return WrapMapper.ok(object);
    }

    @GetMapping("/courseProduct/category/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id获取商品分类")
    @ApiImplicitParam(name ="id", value = "分类id", dataType = "long", paramType = "path")
    public Wrapper getItem(@PathVariable Long id) {
        PmsCourseProductCategory courseProductCategory = courseProductCategoryService.selectByKey( id );
        return WrapMapper.ok(courseProductCategory);
    }

    @PostMapping("/courseProduct/category/delete/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除商品分类")
    @ApiImplicitParam(name ="id", value = "分类id", dataType = "long", paramType = "path")
    public Wrapper delete(@PathVariable Long id) {
        int count = courseProductCategoryService.deleteCourseProductCategory( id );
        return handleResult( count );
    }

    @PostMapping("/category/showStatus/update/{id}")
    @ApiOperation(httpMethod = "POST", value = "修改显示状态")
    @ApiImplicitParams( {@ApiImplicitParam(name ="ids", value = "分类id", dataType = "long", paramType = "query"),
                        @ApiImplicitParam(name ="showStatus", value = "显示状态", dataType = "int", paramType = "query")
    } )
    public Wrapper updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = courseProductCategoryService.updateShowStatus(ids, showStatus);
        return handleResult( count );
    }

    @PostMapping("/categoryIds/findByIds")
    @ApiOperation(httpMethod = "POST", value = "根据id查询父id")
    @ApiImplicitParam(name ="ids", value = "分类id", dataType = "long", paramType = "path")
    public Wrapper findParentIdsById(@RequestParam("ids") List<Long> ids) {
        List<Map> parentIds  = courseProductCategoryService.findParentIdsById(ids);
        return handleResult( parentIds );
    }

}

