package com.dmd.mall.web.oms;

import com.dmd.mall.service.DiveLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ChenYanbing
 * @title: DiveLogController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1113:39
 */
@Controller
@Api(tags = "DiveLogController", description = "我的日志")
@RequestMapping("/diveLog")
public class DiveLogController {


    @Autowired
    private DiveLogService diveLogService;

    


}
