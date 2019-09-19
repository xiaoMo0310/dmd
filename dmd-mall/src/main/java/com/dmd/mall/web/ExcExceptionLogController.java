package com.dmd.mall.web;


import com.dmd.mall.service.ExcExceptionLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 全局异常记录 前端控制器
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-18
 */
@RestController
@RequestMapping("/excExceptionLog")
@Api(value = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExcExceptionLogController {

    @Autowired
    private ExcExceptionLogService excExceptionLogService;


}

