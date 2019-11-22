package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.OmsOrderReturnReason;
import com.dmd.mall.service.OmsOrderReturnReasonService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/omsOrderReturnReason")
@Api(tags = "OmsOrderReturnReasonController", description = "退款原因中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsOrderReturnReasonController extends BaseController {

    @Autowired
    private OmsOrderReturnReasonService omsOrderReturnReasonService;

    @GetMapping("/returnReason/query")
    @ApiOperation(httpMethod = "GET", value = "查询所有的退款原因")
    public Wrapper queryOrderReturnReason() {
        List<OmsOrderReturnReason> omsOrderReturnReasons = omsOrderReturnReasonService.queryOrderReturnReason();
        return WrapMapper.ok(omsOrderReturnReasons);
    }


}

