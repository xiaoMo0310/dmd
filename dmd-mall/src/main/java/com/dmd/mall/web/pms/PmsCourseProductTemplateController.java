package com.dmd.mall.web.pms;

import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.PmsCourseProductTemplateVo;
import com.dmd.mall.service.PmsCourseProductTemplateService;
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

/**
 * <p>
 * 潜水产品模板表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCourseProductTemplateController", description = "产品模板中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCourseProductTemplateController extends BaseController {

    @Autowired
    private PmsCourseProductTemplateService courseProductTemplateService;

    @PostMapping("/template/findByShopId")
    @ApiOperation(httpMethod = "POST", value = "查询潜水学证商品的分类信息")
    @ApiImplicitParams( {@ApiImplicitParam(name ="shopId", value = "店铺Id(如果是平台模板为0)", dataType = "long", paramType = "query"),
                        @ApiImplicitParam(name ="baseQuery", value = "分页信息", dataType = "BaseQuery", paramType = "body")} )
    public Wrapper getList(@RequestParam Long shopId, @RequestBody BaseQuery baseQuery) {
        PageInfo<PmsCourseProductTemplateVo> courseProductTemplatePageInfo= courseProductTemplateService.findTemplateByShopId(shopId, baseQuery);
        return WrapMapper.ok(courseProductTemplatePageInfo);
    }

}

