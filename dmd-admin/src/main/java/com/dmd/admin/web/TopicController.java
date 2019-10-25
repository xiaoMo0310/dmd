package com.dmd.admin.web;

import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.service.TopicService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: TopicController
 * @projectName dmd-masters
 * @description: TODO 后台--话题管理
 * @date 2019/10/179:54
 */
@Controller
@Api(tags = "TopicController", description = "后台--话题管理")
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;


    /**
     * 分页查询话题分类，热度排序
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询话题分类,按照热度(话题下动态数量)排序/条查")
    @RequestMapping(value = "/selectTopicPage",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TopicBean>> queryTopicPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              TopicBean topicBean) {
        List<TopicBean> topicList = topicService.queryTopicPage(pageNum,pageSize,topicBean);
        return CommonResult.success(CommonPage.restPage(topicList));
    }

    /**
     * 添加/修改 话题
     * @param topicBean
     * @return
     */
    @ApiOperation("添加/修改 话题")
    @RequestMapping(value = "/addOrUpdateTopic",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addOrUpdateTopic(@RequestParam Integer id,@RequestBody TopicBean topicBean) {

        try {
            //有id修改,无id新增
            if(id==null) {
                int count = topicService.addTopic(topicBean);
                if (count > 0) {
                    return CommonResult.success(count,"添加成功");
                }
                return CommonResult.failed("添加失败");
            }
            else{
                topicBean.setId(id);
                int count = topicService.updateTopicById(topicBean);
                if (count > 0) {
                    return CommonResult.success(count,"修改成功");
                }
                return CommonResult.failed("修改失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return CommonResult.failed();
        }
    }

    /**
     * 添加/修改 话题
     * @param topicBean
     * @return
     */
    @ApiOperation("添加话题")
    @RequestMapping(value = "/addTopic",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addTopic(@RequestBody TopicBean topicBean) {

        int count = topicService.addTopic(topicBean);
        if (count > 0) {
            return CommonResult.success(count,"添加成功");
        }
        return CommonResult.failed("添加失败");
    }


    /**
     * 回显话题
     * @param id
     * @return
     */
    @ApiOperation("回显话题")
    @RequestMapping(value = "/findTopicInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TopicBean> findTopicInfoById(@RequestParam Long id){
        TopicBean topicBean = topicService.findTopicInfoById(id);
        return CommonResult.success(topicBean);
    }

    /**
     * 批量删除话题
     * @param ids
     * @return
     */
    @ApiOperation("批量删除话题")
    @RequestMapping(value = "/deleteTopicById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteTopicById(@RequestParam("ids") List<Long> ids){
        int count = topicService.deleteTopicById(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
