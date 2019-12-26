package com.dmd.admin.web;

import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.UmsProblemFeedbackBean;
import com.dmd.admin.model.domain.UmsUserFeedbackBean;
import com.dmd.admin.service.UmsFeedbackManagementService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
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

    @OperationLog(content = "批量删除用户意见反馈")
    @ApiOperation("修改问题反馈启动/禁用")
    @RequestMapping(value = "/saveOrUpdateStatus",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult saveOrUpdateStatus(@RequestParam Long id,@RequestParam Integer status) {
        UmsProblemFeedbackBean umsProblemFeedbackBean = new UmsProblemFeedbackBean();
        umsProblemFeedbackBean.setId(id);
        umsProblemFeedbackBean.setStatus(status);
        int count = umsFeedbackManagementService.saveOrUpdateStatus(umsProblemFeedbackBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }

    @OperationLog(content = "修改问题反馈")
    @ApiOperation("修改问题反馈")
    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateFeedback(@RequestParam Long id,
                                       @RequestParam String questionName,
                                       @RequestParam Integer status,
                                       @RequestParam Date createTime
    ) {
        UmsProblemFeedbackBean umsProblemFeedbackBean = new UmsProblemFeedbackBean();
        umsProblemFeedbackBean.setId(id);
        umsProblemFeedbackBean.setQuestionName(questionName);
        umsProblemFeedbackBean.setStatus(status);
        umsProblemFeedbackBean.setCreateTime(createTime);
        umsProblemFeedbackBean.setUpdateTime(new Date());
        int count = umsFeedbackManagementService.updateFeedback(umsProblemFeedbackBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }

    /**
     * 添加问题反馈
     * @param questionName
     * @return
     */
    @ApiOperation("添加问题反馈")
    @RequestMapping(value = "/addFeedback",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addFeedback(@RequestParam String questionName) {
        UmsProblemFeedbackBean umsProblemFeedbackBean = new UmsProblemFeedbackBean();
        umsProblemFeedbackBean.setQuestionName(questionName);
        umsProblemFeedbackBean.setStatus(0);
        umsProblemFeedbackBean.setCreateTime(new Date());
        int count = umsFeedbackManagementService.addFeedback(umsProblemFeedbackBean);
        if (count > 0) {
            return CommonResult.success(count,"添加成功");
        }
        return CommonResult.failed("添加失败");
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    /**
     * 查询用户意见反馈类型
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("查询用户意见反馈类型")
    @RequestMapping(value = "/selectUserFeedback",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsUserFeedbackBean>> queryUserFeedback(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                           UmsUserFeedbackBean umsUserFeedbackBean) {
        if(umsUserFeedbackBean.getStartTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsUserFeedbackBean.getStartTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsUserFeedbackBean.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsUserFeedbackBean.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsUserFeedbackBean.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsUserFeedbackBean.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<UmsUserFeedbackBean> umsProblemFeedbackBeans = umsFeedbackManagementService.queryUserFeedback(pageNum,pageSize,umsUserFeedbackBean);
        return CommonResult.success(CommonPage.restPage(umsProblemFeedbackBeans));
    }

    /**
     * 意见反馈类别查询
     * @return
     */
    @ApiOperation("意见反馈类别查询")
    @RequestMapping(value = "/queryProblemFeedbackByName",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsProblemFeedbackBean>> queryProblemFeedbackByName() {
        List<UmsProblemFeedbackBean> umsProblemFeedbackBeans = umsFeedbackManagementService.queryProblemFeedbackByName();
        return CommonResult.success(umsProblemFeedbackBeans);
    }

    /**
     * 批量删除用户意见反馈
     * @param ids
     * @return
     */
    @OperationLog(content = "批量删除用户意见反馈")
    @ApiOperation("批量删除用户意见反馈")
    @RequestMapping(value = "/deleteUserFeedback",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteUserFeedback(@RequestParam("ids") List<Long> ids){
        int count = umsFeedbackManagementService.deleteUserFeedback(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
