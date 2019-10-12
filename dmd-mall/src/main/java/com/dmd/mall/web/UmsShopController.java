package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.UmsShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 店铺表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@RestController
@RequestMapping("/umsShop")
@Api(tags = "UmsShopController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsShopController extends BaseController {

    @Autowired
    private UmsShopService umsShopService;


}

