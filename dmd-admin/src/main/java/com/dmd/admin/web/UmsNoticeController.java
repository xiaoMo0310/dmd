package com.dmd.admin.web;


import com.dmd.admin.model.dto.MessageDto;
import com.dmd.admin.model.dto.MessageListDto;
import com.dmd.admin.model.vo.NoticeListVo;
import com.dmd.admin.service.UmsNoticeService;
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
 * 用户通告表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsNoticeController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsNoticeController extends BaseController {

    @Autowired
    private UmsNoticeService umsNoticeService;

    @PostMapping("/message/send/{id}/{userType}")
    @ApiOperation(httpMethod = "POST", value = "保存通知信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="id", value = "用户id", paramType = "path", dataType = "Long"),
                    @ApiImplicitParam(name ="userType", value = "用户类型(1:普通用户)", paramType = "path", dataType = "int"),
                    @ApiImplicitParam(name ="messageDto", value = "要保存的信息", paramType = "body", dataType = "MessageDto")})
    public Wrapper saveMessage(@PathVariable("id") Long id, @PathVariable Integer userType, @RequestBody MessageDto messageDto) {
        umsNoticeService.addMessage(getLoginAuthDto(),id, userType, messageDto);
        return WrapMapper.ok();
    }

    @PostMapping("/message/sendAll/{userType}")
    @ApiOperation(httpMethod = "POST", value = "保存所有用户通知信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="userType", value = "用户类型(1:普通用户)", paramType = "path", dataType = "int"),
                        @ApiImplicitParam(name ="messageDto", value = "要保存的信息", paramType = "body", dataType = "MessageDto")})
    public Wrapper saveAllMessage(@PathVariable Integer userType, @RequestBody MessageDto messageDto) {
        umsNoticeService.addAllMessage(getLoginAuthDto(), userType, messageDto);
        return WrapMapper.ok();
    }

    @PostMapping("/message/batchSend/{userType}")
    @ApiOperation(httpMethod = "POST", value = "保存通知信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="ids", value = "用户id集合", paramType = "body", dataType = "List"),
                    @ApiImplicitParam(name ="userType", value = "用户类型(1:普通用户)", paramType = "path", dataType = "int"),
                    @ApiImplicitParam(name ="messageDto", value = "要保存的信息", paramType = "body", dataType = "MessageDto")})
    public Wrapper batchSaveMessage(@PathVariable Integer userType, @RequestParam("ids") List<Long> ids, @RequestBody MessageDto messageDto) {
        umsNoticeService.batchAddMessage(getLoginAuthDto(),ids, userType, messageDto);
        return WrapMapper.ok();
    }

    @PostMapping("/message/list")
    @ApiOperation(httpMethod = "POST", value = "分页查询通知消息")
    @ApiImplicitParams({@ApiImplicitParam(name ="messageListDto", value = "查询需要的信息", paramType = "body", dataType = "MessageListDto")})
    public Wrapper batchSaveMessage(@RequestBody MessageListDto messageListDto) {
        PageInfo<NoticeListVo> pageInfo = umsNoticeService.findNoticeMessageByPage(messageListDto);
        return WrapMapper.ok(pageInfo);
    }



}
