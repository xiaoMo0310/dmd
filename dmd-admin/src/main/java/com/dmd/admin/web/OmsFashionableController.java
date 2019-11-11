package com.dmd.admin.web;


import com.dmd.admin.model.domain.OmsFashionable;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.admin.service.OmsFashionableService;
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
 * 分账表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/oms")
@Api(tags = "OmsFashionableController", description = "分账中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsFashionableController extends BaseController {

    @Autowired
    private OmsFashionableService omsFashionableService;

    @PostMapping("/fashionableList/find")
    @ApiOperation(httpMethod = "POST", value = "查询待分账的账单")
    @ApiImplicitParam(name ="billingDetailDto", value = "搜索分页数据", paramType = "body", dataType = "BillingDetailDto")
    public Wrapper findFashionableList(@RequestBody BillingDetailDto billingDetailDto) {
        PageInfo<OmsFashionable> pageInfo = omsFashionableService.findFashionableList(billingDetailDto);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/fashionableStatus/update/{collectingNo}")
    @ApiOperation(httpMethod = "POST", value = "根据分账单号修改分账状态为异常待处理")
    @ApiImplicitParam(name ="collectingNo", value = "分账单号", paramType = "path", dataType = "String")
    public Wrapper updateFashionableStatus(@PathVariable String collectingNo) {
        int result = omsFashionableService.updateFashionableStatus(getLoginAuthDto(), collectingNo, 3);
        return handleResult(result);
    }
}

