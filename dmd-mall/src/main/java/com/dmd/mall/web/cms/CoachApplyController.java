package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CoachApplyBean;
import com.dmd.mall.service.CoachApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChenYanbing
 * @title: CoachApplyController
 * @projectName dmd-master
 * @description: 用户教练申请
 * @date 2019/9/1914:41
 */
@Controller
@Api(tags = "CoachApplyController", description = "教练申请")
@RequestMapping("/coach")
public class CoachApplyController {

    @Autowired
    private CoachApplyService coachApplyService;

    @ApiOperation("教练申请提交审核资料")
    @RequestMapping(value = "/addCoachApply", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addCoachApply(@RequestBody CoachApplyBean coachApplyBean//HttpServletRequest request
                                       ){
        //获取用户id
        //String token = request.getHeader("token");
        //token配置类获取用户token
        //JWTResult result = JWTUtils.checkToken(token);
        //获取登陆id
        //Long userId = result.getUserId();
        //关联用户ID
        coachApplyBean.setUserId((long) 1);
        //去新增

        int count = coachApplyService.addCoachApply(coachApplyBean);
        if (count > 0) {
            return CommonResult.success(count,"提交申请成功");
        }
        return CommonResult.failed("提交申请失败");
    }

}
