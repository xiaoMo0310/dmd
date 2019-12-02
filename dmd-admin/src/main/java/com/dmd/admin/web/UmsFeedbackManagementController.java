package com.dmd.admin.web;

import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.model.domain.UmsProblemFeedbackBean;
import com.dmd.admin.service.UmsFeedbackManagementService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackManagementController
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/211:01
 */
@Controller
@Api(tags = "UmsFeedbackManagementController", description = "问题反馈管理")
@RequestMapping("/feedback")
public class UmsFeedbackManagementController {

    @Autowired
    private UmsFeedbackManagementService umsFeedbackManagementService;

    /**
     * 查询意见反馈列表
     * @return
     */
    @ApiOperation("查询意见反馈类型")
    @RequestMapping(value = "/selectProblemFeedback",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsProblemFeedbackBean>> queryProblemFeedback(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsProblemFeedbackBean> umsProblemFeedbackBeans = umsFeedbackManagementService.queryProblemFeedback(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(umsProblemFeedbackBeans));
    }
}
