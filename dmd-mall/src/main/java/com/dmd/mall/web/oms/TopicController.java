package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicController
 * @projectName dmd-master
 * @description: TODO 首页-话题
 * @date 2019/9/2910:41
 */
@Controller
@Api(tags = "TopicController", description = "首页-话题")
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;


    /**
     * 分页查询话题分类，热度排序
     * @param userId
     * @return
     */
    @ApiOperation("分页查询话题分类,按照热度(话题下动态数量)排序")
    @RequestMapping(value = "/selectTopicPage",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TopicBean>> queryTopicPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<TopicBean> topicList = topicService.queryTopicPage(pageNum,pageSize);
        return CommonResult.success(topicList);
    }


    /**
     * 话题详情查询
     * @param 话题id
     * @return
     */
    @ApiOperation("话题详情查询")
    @RequestMapping(value = "/selectTopicById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TopicBean>> queryTopicById (@RequestParam Integer id){
        List<TopicBean> topicList = topicService.queryTopicById(id);
        return CommonResult.success(topicList);
    }
}
