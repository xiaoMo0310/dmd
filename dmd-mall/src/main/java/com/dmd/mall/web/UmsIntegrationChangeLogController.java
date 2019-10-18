package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.UmsIntegrationChangeLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分变化历史记录表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-18
 */
@RestController
@RequestMapping("/umsIntegrationChangeLog")
@Api(tags = "UmsIntegrationChangeLogController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsIntegrationChangeLogController extends BaseController {

    @Autowired
    private UmsIntegrationChangeLogService umsIntegrationChangeLogService;


}

