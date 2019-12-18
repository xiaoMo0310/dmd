package com.dmd.admin.web;

import com.dmd.admin.model.domain.DynamicBean;
import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.service.DynamicService;
import com.dmd.annotation.NoNeedAccessAuthentication;
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
 * @title: DynamicController
 * @projectName dmd-master
 * @description: TODO 后台--动态管理
 * @date 2019/9/239:18
 */
@Controller
@Api(tags = "DynamicController", description = "后台--动态管理")
@RequestMapping("/dynamic")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;


    /**
     * 分页查询用户动态
     * @param pageNum
     * @param pageSize
     * @param dynamicBean
     * @return
     */
    @ApiOperation("分页查询用户动态/条查")
    @RequestMapping(value = "/selectDynamicPage",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<DynamicBean>> queryDynamicPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  DynamicBean dynamicBean) {
        if(dynamicBean.getStratTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dynamicBean.getStratTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                dynamicBean.setStratTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(dynamicBean.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dynamicBean.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                dynamicBean.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<DynamicBean> dynamicList = dynamicService.queryDynamicPage(pageNum,pageSize,dynamicBean);
        return  CommonResult.success(CommonPage.restPage(dynamicList));
    }

    /**
     * 话题类别查询
     * @return
     */
    @ApiOperation("话题类别查询")
    @RequestMapping(value = "/selectTopic",method = RequestMethod.GET)
    @ResponseBody
    @NoNeedAccessAuthentication
    public CommonResult<List<TopicBean>> queryTopicPage() {
        List<TopicBean> topicList = dynamicService.queryTopic();
        return CommonResult.success(topicList);
    }

    /**
     * 批量删除话题
     * @param ids
     * @return
     */
    @ApiOperation("批量删除用户动态")
    @RequestMapping(value = "/updateDynamicDelflagById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDynamicDelflagById(@RequestParam("ids") List<Long> ids){
        int count = dynamicService.updateDynamicDelflagById(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 根据动态id查询动态详情
     * @param id
     * @return
     */
    @ApiOperation("根据动态id查询动态详情")
    @RequestMapping(value = "/selectDynamicById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<DynamicBean> queryDynamicById(@RequestParam Long id,@RequestParam Integer userType) {
        DynamicBean dynamicList = dynamicService.selectDynamicById(id,userType);
        return CommonResult.success(dynamicList);
    }
}
