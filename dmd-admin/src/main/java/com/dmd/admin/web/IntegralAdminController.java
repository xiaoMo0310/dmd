package com.dmd.admin.web;

import com.dmd.admin.model.domain.IntegralRuleBean;
import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.model.domain.UmsIntegrationChangeHistory;
import com.dmd.admin.service.IntegralAdminService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralAdminController
 * @projectName dmd-masters
 * @description: TODO 后台--积分管理
 * @date 2019/10/2214:18
 */
@Controller
@Api(tags = "IntegralAdminController", description = "后台--积分管理")
@RequestMapping("/integral")
public class IntegralAdminController {

    @Autowired
    private IntegralAdminService integralAdminService;

    /**
     * 积分规则说明查询
     * @return
     */
    @ApiOperation("查询积分规则说明")
    @RequestMapping(value = "/selectIntegralRule",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<IntegralRuleBean>> queryIntegralRule(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<IntegralRuleBean> List = integralAdminService.queryIntegralRule();
        return CommonResult.success(CommonPage.restPage(List));
    }

    /**
     * 积分规则修改
     * @param integralRuleBean
     * @return
     */
    @ApiOperation("修改积分规则说明")
    @RequestMapping(value = "/updateIntegralRule",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralRule(@RequestBody IntegralRuleBean integralRuleBean) {
        int count = integralAdminService.updateIntegralRule(integralRuleBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");

    }

    /**
     * 回显积分规则
     * @param id
     * @return
     */
    @ApiOperation("回显积分规则")
    @RequestMapping(value = "/findIntegralInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntegralRuleBean> findIntegralInfoById(@RequestParam Long id){
        IntegralRuleBean integralRuleBean = integralAdminService.findIntegralInfoById(id);
        return CommonResult.success(integralRuleBean);
    }


    /**
     * 查询积分明细记录
     * @param pageNum
     * @param pageSize
     * @param umsIntegrationChangeHistory
     * @return
     */
    @ApiOperation("查询积分明细记录")
    @RequestMapping(value = "/selectIntegralChange",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsIntegrationChangeHistory>> queryIntegralChangePage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                                         UmsIntegrationChangeHistory umsIntegrationChangeHistory
    ) {

        if(umsIntegrationChangeHistory.getStartTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsIntegrationChangeHistory.getStartTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsIntegrationChangeHistory.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsIntegrationChangeHistory.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsIntegrationChangeHistory.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsIntegrationChangeHistory.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<UmsIntegrationChangeHistory> List = integralAdminService.queryIntegralChangePage(pageNum,pageSize,umsIntegrationChangeHistory);
        return CommonResult.success(CommonPage.restPage(List));
    }


    /**
     * 增加用户积分操作
     * @param id
     * @param umsIntegrationChangeHistory
     * @return
     */
    @ApiOperation("增加用户积分操作")
    @RequestMapping(value = "/addIntegration",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegration(@RequestParam Long id,@RequestBody UmsIntegrationChangeHistory umsIntegrationChangeHistory) {
        umsIntegrationChangeHistory.setId(id);
        int count = integralAdminService.updateIntegration(umsIntegrationChangeHistory);
        if (count > 0) {
            return CommonResult.success(count,"增加成功");
        }
        return CommonResult.failed("增加失败");
    }

    /**
     * 减少用户积分操作
     * @param id
     * @param umsIntegrationChangeHistory
     * @return
     */
    @ApiOperation("减少用户积分操作")
    @RequestMapping(value = "/reduceIntegration",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegrationReduce(@RequestParam Long id,@RequestBody UmsIntegrationChangeHistory umsIntegrationChangeHistory) {
        umsIntegrationChangeHistory.setId(id);
        int count = integralAdminService.updateIntegrationReduce(umsIntegrationChangeHistory);
        if (count > 0) {
            return CommonResult.success(count,"减少成功");
        }
        return CommonResult.failed("减少失败");
    }

}
