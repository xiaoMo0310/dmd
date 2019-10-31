package com.dmd.admin.web;

import com.dmd.admin.model.domain.DiveLogAirbottleBean;
import com.dmd.admin.model.domain.DiveLogBean;
import com.dmd.admin.service.DiveLogService;
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
 * @title: DiveLogController
 * @projectName dmd-masters
 * @description: TODO 我的日志
 * @date 2019/10/129:09
 */
@Controller
@Api(tags = "DiveLogController", description = "后台--潜水日志管理")
@RequestMapping("/diveLog")
public class DiveLogController {


    @Autowired
    private DiveLogService diveLogService;

    /**
     * 分页查询用户日志
     * @param pageNum
     * @param pageSize
     * @param diveLogBean
     * @return
     */
    @ApiOperation("分页查询用户潜水日志")
    @RequestMapping(value = "/selectDiveLogAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<DiveLogBean>> queryDiveLogPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  DiveLogBean diveLogBean
    ) {
        if(diveLogBean.getStartDate() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(diveLogBean.getStartDate());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                diveLogBean.setStartDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(diveLogBean.getEndDate() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(diveLogBean.getEndDate());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                diveLogBean.setEndDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<DiveLogBean> diveLogAllList = diveLogService.queryDiveLogPage(pageNum,pageSize,diveLogBean);
        return CommonResult.success(CommonPage.restPage(diveLogAllList));
    }



    /**
     * 根据日志id查询气瓶消耗
     * @param id
     * @return
     */
    @ApiOperation("查询潜水日志的气瓶消耗")
    @RequestMapping(value = "/selectDiveLogAirbottleByDiveLogId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<DiveLogAirbottleBean>> queryDiveLogAirbottleByDiveLogId(
                                                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                    @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                                                    @RequestParam Long id){
        List<DiveLogAirbottleBean> diveLogList = diveLogService.queryDiveLogAirbottleByDiveLogId(id);
        return CommonResult.success(CommonPage.restPage(diveLogList));
    }

    /**
     * 批量删除动态或者日志评论
     * @param ids
     * @return
     */
    @ApiOperation("批量删除潜水日志")
    @RequestMapping(value = "/updateCommentDelflag",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDiveLogDelflag(@RequestParam("ids") List<Long> ids){
        int count = diveLogService.updateDiveLogDelflag(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
