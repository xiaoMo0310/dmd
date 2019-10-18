package com.dmd.mall.web;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<List<DynamicBean>> queryDynamicPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, DynamicBean dynamicBean) {
        List<DynamicBean> dynamicList = dynamicService.queryDynamicPage(pageNum,pageSize,dynamicBean);
        return CommonResult.success(dynamicList);
    }

    /**
     * 话题类别查询
     * @return
     */
    @ApiOperation("话题类别查询")
    @RequestMapping(value = "/selectTopic",method = RequestMethod.GET)
    @ResponseBody
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
    public CommonResult updateDynamicDelflagById(@RequestBody String... ids){
        int count = dynamicService.updateDynamicDelflagById(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
