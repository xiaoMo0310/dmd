package com.dmd.admin.web;


import com.dmd.admin.service.OmsShippingService;
import com.dmd.core.support.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收货人信息表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/omsShipping")
@Api(tags = "OmsShippingController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsShippingController extends BaseController {

    @Autowired
    private OmsShippingService omsShippingService;


}

