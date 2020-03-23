package com.dmd.mall.web.cms;


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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/advertisePic/find")
    @ApiOperation(httpMethod = "GET", value = "根据显示位置查询图片")
    @ApiImplicitParam(name ="type", value = "轮播位置：1->app首页轮播；2->app商场轮播图；3->启动页 4->引导页", dataType = "int", paramType = "query")
    public Wrapper findAdvertisePicList(@RequestParam("type") Integer type) {
        List<SmsHomeAdvertise> pics = smsHomeAdvertiseService.selectAdvertisePicList(type);
        return WrapMapper.ok(pics);
    }

}

