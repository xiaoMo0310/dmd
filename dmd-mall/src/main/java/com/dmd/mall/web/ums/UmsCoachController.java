package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.UmsCoachService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 教练表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsCoachController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachController extends BaseController {

    @Autowired
    private UmsCoachService umsCoachService;


}

