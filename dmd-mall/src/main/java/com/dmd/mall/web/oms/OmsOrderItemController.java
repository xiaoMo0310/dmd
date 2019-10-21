package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.OmsOrderItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单详情表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@RestController
@RequestMapping("/omsOrderItem")
@Api(tags = "OmsOrderItemController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsOrderItemController extends BaseController {

    @Autowired
    private OmsOrderItemService omsOrderItemService;


}

