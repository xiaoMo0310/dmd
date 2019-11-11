package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.SmsHomeAdvertise;
import com.dmd.mall.service.SmsHomeAdvertiseService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@RestController
@RequestMapping("/sms")
@Api(value = "图片中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SmsHomeAdvertiseController extends BaseController {

    @Autowired
    private SmsHomeAdvertiseService smsHomeAdvertiseService;

     @GetMapping("/advertisePic/find/{type}")
    @ApiOperation(httpMethod = "GET", value = "根据显示位置查询图片")
    @ApiImplicitParam(name ="type", value = "轮播位置：1->app首页轮播；2->app商场轮播图；3->启动页 4->引导页", dataType = "int", paramType = "path")
    public Wrapper findAdvertisePicList(@PathVariable Integer type) {
        List<SmsHomeAdvertise> pics = smsHomeAdvertiseService.selectAdvertisePicList(type);
        return WrapMapper.ok(pics);
    }

}

