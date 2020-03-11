package com.dmd.mall.web.cms;

import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.SmsShopActivity;
import com.dmd.mall.service.SmsShopActivityService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/sms")
@Api(tags = "SmsShopActivityController", description = "店铺活动中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SmsShopActivityController extends BaseController {

    @Autowired
    private SmsShopActivityService shopActivityService;

    @PostMapping("/shopActivity/findByPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询店铺活动")
    @ApiImplicitParam(name ="baseQuery", value = "分页信息", dataType = "BaseQuery", paramType = "body")
    public Wrapper getList(@RequestBody BaseQuery baseQuery) {
        PageInfo<SmsShopActivity> shopActivityByPage= shopActivityService.findShopActivityByPage(baseQuery);
        return WrapMapper.ok(shopActivityByPage);
    }

}

