package com.dmd.admin.web;

import com.dmd.admin.model.domain.SmsShopActivity;
import com.dmd.admin.service.SmsShopActivityService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/sms")
@Api(tags = "SmsShopActivityController", description = "店铺活动中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SmsShopActivityController extends BaseController {

    @Autowired
    private SmsShopActivityService shopActivityService;

    @PostMapping("/activity/createOrUpdate")
    @ApiOperation(httpMethod = "POST", value = "添加或修改店铺活动")
    @ApiImplicitParam(name ="activityshopActivity", value = "添加活动数据", dataType = "SmsShopActivity", paramType = "body")
    public Wrapper create(@RequestBody SmsShopActivity shopActivity) {
        int count = shopActivityService.createOrUpdateActivity(getLoginAuthDto(), shopActivity);
        return handleResult( count );
    }

    @PostMapping("/activity/findByPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询店铺活动")
    @ApiImplicitParam(name ="smsShopActivity", value = "查询需要的数据", dataType = "SmsShopActivity", paramType = "body")
    public Wrapper getList(@RequestBody SmsShopActivity smsShopActivity) {
        PageInfo<SmsShopActivity> shopActivityPageInfo = shopActivityService.getActivityList(smsShopActivity);
        return WrapMapper.ok(shopActivityPageInfo);
    }

    @GetMapping("/activity/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id获取店铺活动")
    @ApiImplicitParam(name ="id", value = "模板id", dataType = "long", paramType = "path")
    public Wrapper getItem(@PathVariable Long id) {
        SmsShopActivity shopActivity = shopActivityService.selectByKey( id );
        return WrapMapper.ok(shopActivity);
    }

    @PostMapping("/activity/delete")
    @ApiOperation(httpMethod = "POST", value = "删除商品模板")
    @ApiImplicitParam(name ="ids", value = "活动id", dataType = "list", paramType = "query")
    public Wrapper delete(@RequestParam List<Long> ids) {
        int count = shopActivityService.deleteActivity( ids );
        return handleResult( count );
    }

    @PostMapping("/activity/showStatus/update/{id}")
    @ApiOperation(httpMethod = "POST", value = "修改显示状态")
    @ApiImplicitParams( {@ApiImplicitParam(name ="ids", value = "活动id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name ="showStatus", value = "显示状态", dataType = "int", paramType = "query")
    } )
    public Wrapper updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = shopActivityService.updateShowStatus(ids, showStatus);
        return handleResult( count );
    }
}

