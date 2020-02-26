package com.dmd.mall.web.ums;

import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.UmsShopReply;
import com.dmd.mall.model.dto.UmsShopReplyDto;
import com.dmd.mall.service.UmsShopReplyService;
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
 * 教练店铺自动回复 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@RestController
@RequestMapping("/umsShopReply")
@Api(tags = "UmsShopReplyController", description = "教练商铺自动回复信息中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsShopReplyController extends BaseController {

    @Autowired
    private UmsShopReplyService umsShopReplyService;

    @PostMapping("/reply/findByPage")
    @ApiOperation(httpMethod = "GET", value = "分页查询教练自动回复的信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery", paramType = "body")
    public Wrapper findLoginUserMessage(@RequestBody BaseQuery baseQuery) {
        PageInfo<UmsShopReply> umsShopReplyPageInfo = umsShopReplyService.findReplyMessageByPage(getLoginAuthDto(), baseQuery);
        return WrapMapper.ok(umsShopReplyPageInfo);
    }

    @PostMapping("/reply/addOrEdit")
    @ApiOperation(httpMethod = "GET", value = "添加或者编辑消息自动回复")
    @ApiImplicitParam(name ="umsShopReplyDto", value = "自动回复消息数据", dataType = "UmsShopReplyDto", paramType = "body")
    public Wrapper findLoginUserMessage(@RequestBody UmsShopReplyDto umsShopReplyDto) {
        int result = umsShopReplyService.addOrEditReplyMessage(getLoginAuthDto(), umsShopReplyDto);
        return handleResult( result );
    }


}

