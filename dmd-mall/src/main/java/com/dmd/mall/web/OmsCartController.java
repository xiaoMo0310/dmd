package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.OmsCartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-12
 */
@RestController
@RequestMapping("/omsCart")
@Api(tags = "OmsCartController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsCartController extends BaseController {

    @Autowired
    private OmsCartService omsCartService;


}

