package com.dmd.mall.web.ums;

import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.UmsShopNotice;
import com.dmd.mall.service.UmsShopNoticeService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "UmsShopNoticeController", description = "店铺公告通知", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsShopNoticeController extends BaseController {

    @Autowired
    private UmsShopNoticeService umsShopNoticeService;

    @PostMapping("/shopNotice/findByPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询全部店铺公告信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", paramType = "BaseQuery", dataType = "body")
    public Wrapper findShopNoticeList(@RequestBody BaseQuery baseQuery) {
        PageInfo<UmsShopNotice> shopNoticePageInfo = umsShopNoticeService.selectShopNoticeByPage(getLoginAuthDto(), baseQuery);
        return WrapMapper.ok(shopNoticePageInfo);
    }

    @GetMapping("/shopNotice/findById")
    @ApiOperation(httpMethod = "GET", value = "根据id店铺公告信息")
    @ApiImplicitParam(name ="id", value = "公告信息id", paramType = "long", dataType = "query")
    public Wrapper findShopNoticeList(@RequestParam Long id) {
        UmsShopNotice umsShopNotice = umsShopNoticeService.selectByKey( id );
        return WrapMapper.ok(umsShopNotice);
    }

}

