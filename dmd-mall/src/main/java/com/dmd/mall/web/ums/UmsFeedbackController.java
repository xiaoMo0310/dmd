package com.dmd.mall.web.ums;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.UmsProblemFeedbackBean;
import com.dmd.mall.model.domain.UmsUserFeedbackBean;
import com.dmd.mall.service.UmsFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackController
 * @projectName dmd
 * @description: 用户意见反馈
 * @date 2019/12/29:43
 */
@Controller
@Api(tags = "UmsFeedbackController", description = "用户意见反馈")
@RequestMapping("/feedback")
public class UmsFeedbackController {

    @Autowired
    private UmsFeedbackService umsFeedbackService;


    /**
     * 查询意见反馈列表
     * @return
     */
    @ApiOperation("查询意见反馈类型")
    @RequestMapping(value = "/selectProblemFeedback",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsProblemFeedbackBean>> queryProblemFeedback() {
        List<UmsProblemFeedbackBean> umsProblemFeedbackBeans = umsFeedbackService.queryProblemFeedback();
        return CommonResult.success(umsProblemFeedbackBeans);
    }

    /**
     * 用户上传意见反馈
     * @param umsUserFeedbackBean
     * @return
     */
    @ApiOperation("用户上传意见反馈")
    @RequestMapping(value = "/addFeedback",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addFeedback(@RequestBody UmsUserFeedbackBean umsUserFeedbackBean) {
        int count = umsFeedbackService.addFeedback(umsUserFeedbackBean);
        if (count > 0) {
            return CommonResult.success(count,"提交成功！");
        }
        return CommonResult.failed("提交失败！");
    }
}
