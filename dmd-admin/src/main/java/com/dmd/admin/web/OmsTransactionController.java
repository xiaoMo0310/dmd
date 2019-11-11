package com.dmd.admin.web;


import com.dmd.admin.model.domain.OmsTransaction;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.admin.service.OmsTransactionService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
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
 * 订单交易表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/oms")
@Api(tags = "OmsTransactionController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsTransactionController extends BaseController {

    @Autowired
    private OmsTransactionService omsTransactionService;

    @PostMapping("/incomeAndExpenditureDetails/find")
    @ApiOperation(httpMethod = "POST", value = "获取平台的收支明细")
    @ApiImplicitParam(name ="billingDetailDto", value = "搜索分页数据", paramType = "body", dataType = "BillingDetailDto")
    public Wrapper findIncomeAndExpenditureDetails(@RequestBody BillingDetailDto billingDetailDto) {
        PageInfo<OmsTransaction> pageInfo = omsTransactionService.findIncomeAndExpenditureDetails(billingDetailDto);
        return handleResult(pageInfo);
    }

}

