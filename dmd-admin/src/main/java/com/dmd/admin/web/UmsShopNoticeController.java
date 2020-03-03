package com.dmd.admin.web;

import com.dmd.admin.model.domain.UmsShopNotice;
import com.dmd.admin.service.UmsShopNoticeService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 教练店铺公告 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsShopNoticeController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsShopNoticeController extends BaseController {

    @Autowired
    private UmsShopNoticeService umsShopNoticeService;

    @PostMapping("/shopNotice/save")
    @ApiOperation(httpMethod = "POST", value = "保存店铺公告信息")
    @ApiImplicitParam(name ="umsShopNotice", value = "店铺公告信息数据", paramType = "UmsShopNotice", dataType = "body")
    public Wrapper saveMessage(@RequestBody UmsShopNotice umsShopNotice) {
        int result = umsShopNoticeService.saveShopNotice(getLoginAuthDto(), umsShopNotice);
        return handleResult( result );
    }

    @PostMapping("/shopNotice/list")
    @ApiOperation(httpMethod = "POST", value = "查询全部店铺公告信息")
    @ApiImplicitParam(name ="umsShopNotice", value = "店铺公告信息数据", paramType = "UmsShopNotice", dataType = "body")
    public Wrapper findShopNoticeList(@RequestBody UmsShopNotice umsShopNotice) {
        PageInfo<UmsShopNotice> shopNoticePageInfo = umsShopNoticeService.selectShopNoticeByPage(getLoginAuthDto(), umsShopNotice);
        return WrapMapper.ok(shopNoticePageInfo);
    }

    @PostMapping("/shopNotice/delete")
    @ApiOperation(httpMethod = "POST", value = "批量删除公告信息")
    @ApiImplicitParam(name ="ids", value = "公告id", paramType = "long", dataType = "query")
    public Wrapper findShopNoticeList(@RequestParam("ids") List<Long> ids) {
        int result = umsShopNoticeService.batchDeleteShopNotice(getLoginAuthDto(), ids);
        return handleResult( result );
    }

}

