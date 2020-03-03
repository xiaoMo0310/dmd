package com.dmd.mall.web.ums;

import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.UmsShopLevelExplain;
import com.dmd.mall.service.UmsShopLevelExplainService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 教练店铺店铺表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsShopLevelExplainController", description = "店铺等级说明", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsShopLevelExplainController extends BaseController {

    @Autowired
    private UmsShopLevelExplainService umsShopLevelExplainService;

    @PostMapping("/shop/levelExplain/find")
    @ApiOperation(httpMethod = "POST", value = "查询店铺等级说明")
    public Wrapper findShopNoticeList() {
        List<UmsShopLevelExplain> shopLevelExplains = umsShopLevelExplainService.selectAll();
        return WrapMapper.ok(shopLevelExplains.get( 0 ));
    }

}

