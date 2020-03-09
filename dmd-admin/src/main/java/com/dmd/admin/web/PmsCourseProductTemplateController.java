package com.dmd.admin.web;

import com.dmd.admin.model.domain.PmsCourseProductTemplate;
import com.dmd.admin.service.PmsCourseProductTemplateService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
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
 * 潜水产品模板表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductTemplateController", description = "潜水产品模板中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductTemplateController extends BaseController {

    @Autowired
    private PmsCourseProductTemplateService courseProductTemplateService;

    @PostMapping("/courseProduct/template/createOrUpdate")
    @ApiOperation(httpMethod = "POST", value = "添加或修改潜水学证产品模板")
    @ApiImplicitParam(name ="courseProductTemplate", value = "添加模板数据", dataType = "PmsCourseProductTemplate", paramType = "body")
    public Wrapper create(@RequestBody PmsCourseProductTemplate courseProductTemplate) {
        int count = courseProductTemplateService.createOrUpdateTemplate(getLoginAuthDto(), courseProductTemplate);
        return handleResult( count );
    }

    @PostMapping("/courseProduct/templateList")
    @ApiOperation(httpMethod = "POST", value = "分页查询模板信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery", paramType = "body")
    public Wrapper getList(@RequestBody BaseQuery baseQuery) {
        PageInfo<PmsCourseProductTemplate> courseProductTemplates = courseProductTemplateService.getTemplateList(baseQuery);
        return WrapMapper.ok(courseProductTemplates);
    }

    @GetMapping("/courseProduct/template/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id获取商品模板")
    @ApiImplicitParam(name ="id", value = "模板id", dataType = "long", paramType = "path")
    public Wrapper getItem(@PathVariable Long id) {
        PmsCourseProductTemplate courseProductTemplate = courseProductTemplateService.selectByKey( id );
        return WrapMapper.ok(courseProductTemplate);
    }

    @PostMapping("/courseProduct/template/delete/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除商品模板")
    @ApiImplicitParam(name ="id", value = "模板id", dataType = "long", paramType = "path")
    public Wrapper delete(@PathVariable Long id) {
        int count = courseProductTemplateService.deleteCourseProductTemplate( id );
        return handleResult( count );
    }

    @PostMapping("/template/showStatus/update/{id}")
    @ApiOperation(httpMethod = "POST", value = "修改显示状态")
    @ApiImplicitParams( {@ApiImplicitParam(name ="ids", value = "模板id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name ="showStatus", value = "显示状态", dataType = "int", paramType = "query")
    } )
    public Wrapper updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = courseProductTemplateService.updateShowStatus(ids, showStatus);
        return handleResult( count );
    }

}

