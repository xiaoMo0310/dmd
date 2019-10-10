package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@RestController
@RequestMapping("/smsHomeAdvertise")
@Api(value = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SmsHomeAdvertiseController extends BaseController {

    @Autowired
    private SmsHomeAdvertiseService smsHomeAdvertiseService;


}

