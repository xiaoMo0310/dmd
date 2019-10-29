package com.dmd.admin.web;


import com.dmd.admin.service.UmsNoticeMarkService;
import com.dmd.core.support.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户通知标记表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/umsNoticeMark")
@Api(tags = "UmsNoticeMarkController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsNoticeMarkController extends BaseController {

    @Autowired
    private UmsNoticeMarkService umsNoticeMarkService;


}

