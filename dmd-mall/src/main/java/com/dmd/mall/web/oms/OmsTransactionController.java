package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.OmsTransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("/omsTransaction")
@Api(tags = "OmsTransactionController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsTransactionController extends BaseController {

    @Autowired
    private OmsTransactionService omsTransactionService;


}

