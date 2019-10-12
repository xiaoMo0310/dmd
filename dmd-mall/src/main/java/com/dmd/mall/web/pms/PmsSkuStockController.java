package com.dmd.mall.web.pms;


import org.springframework.web.bind.annotation.RequestMapping;
import com.dmd.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import com.dmd.core.support.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@RestController
@RequestMapping("/pmsSkuStock")
@Api(value = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsSkuStockController extends BaseController {

    @Autowired
    private PmsSkuStockService pmsSkuStockService;


}

