package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.DmdIntegralGiftService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/dmd")
@Api(tags = "DmdIntegralGiftController", description = "积分商品中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DmdIntegralGiftController extends BaseController {

    @Autowired
    private DmdIntegralGiftService dmdIntegralGiftService;


}

