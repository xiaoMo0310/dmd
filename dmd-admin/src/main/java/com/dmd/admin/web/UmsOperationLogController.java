package com.dmd.admin.web;


import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.UmsOperationLog;
import com.dmd.base.dto.BaseQuery;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.dmd.admin.service.UmsOperationLogService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import com.dmd.core.support.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/umsOperationLog")
@Api(tags = "UmsOperationLogController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsOperationLogController extends BaseController {

    @Autowired
    private UmsOperationLogService umsOperationLogService;

    @ApiOperation("操作日志记录")
    @RequestMapping(value = "/getOperationLog", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper<PageInfo> getOperationLog(@RequestBody UmsOperationLog umsOperationLog) {
        PageInfo pageInfo = umsOperationLogService.getOperationLog(umsOperationLog);
        return WrapMapper.ok(pageInfo);
    }

}

