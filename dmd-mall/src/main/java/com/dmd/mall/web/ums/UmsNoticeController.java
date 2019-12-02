package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.UmsNoticeVo;
import com.dmd.mall.service.UmsNoticeService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户通告表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsNoticeController", description = "通知信息中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsNoticeController extends BaseController {

    @Autowired
    private UmsNoticeService umsNoticeService;

    @GetMapping("/message/find")
    @ApiOperation(httpMethod = "GET", value = "查询当前登录人的通知信息")
    @ApiImplicitParam(name ="messageType", value = "消息类型(1:系统消息 2:点赞消息 3:评论消息)", dataType = "int", paramType = "query")
    public Wrapper findLoginUserMessage(@RequestParam("messageType") Integer messageType,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize) {
        PageInfo<UmsNoticeVo> umsNoticeVos = umsNoticeService.findLoginUserMessageByPage(getLoginAuthDto(), messageType, pageNum, pageSize);
        return WrapMapper.ok(umsNoticeVos);
    }

}

