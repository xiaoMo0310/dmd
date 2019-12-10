package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.OmsOrderSettingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单设置表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-10
 */
@RestController
@RequestMapping("/omsOrderSetting")
@Api(tags = "OmsOrderSettingController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsOrderSettingController extends BaseController {

    @Autowired
    private OmsOrderSettingService omsOrderSettingService;


}

