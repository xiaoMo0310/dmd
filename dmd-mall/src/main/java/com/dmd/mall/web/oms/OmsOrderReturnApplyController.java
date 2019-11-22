package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.OrderReturnApplyDto;
import com.dmd.mall.service.OmsOrderReturnApplyService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/omsOrderReturnApply")
@Api(tags = "OmsOrderReturnApplyController", description = "退货申请中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsOrderReturnApplyController extends BaseController {

    @Autowired
    private OmsOrderReturnApplyService omsOrderReturnApplyService;

    @PostMapping("/orderReturn/apply")
    @ApiOperation(httpMethod = "POST", value = "退款申请")
    @ApiImplicitParam(name ="returnApplyDto", value = "申请信息", dataType = "OrderReturnApplyDto", paramType = "body")
    public Wrapper orderReturnApply(@RequestBody OrderReturnApplyDto returnApplyDto) {
        int result= omsOrderReturnApplyService.insertOrderReturnMessage(getLoginAuthDto(), returnApplyDto);
        return WrapMapper.ok(result);
    }

}
