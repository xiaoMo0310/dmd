package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.DynamicService;
import com.dmd.mall.service.TopicService;
import com.github.pagehelper.PageInfo;
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
 * @description: 首页--话题
 * @date 2019/9/2910:41
 */
@Controller
@Api(tags = "TopicController", description = "首页-话题")
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private DynamicService dynamicService;


    /**
     * 分页查询话题分类，热度排序
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询话题分类,按照热度(话题下动态数量)排序")
    @RequestMapping(value = "/selectTopicPage",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<TopicBean>> queryTopicPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<TopicBean> topicList = topicService.queryTopicPage(pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(topicList));
    }

    /**
     * 查询话题分类，热度排序
     * @return
     */
    @ApiOperation("话题分类查询,按照热度(话题下动态数量)排序")
    @RequestMapping(value = "/selectTopic",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TopicBean>> queryTopic() {
        List<TopicBean> topicList = topicService.queryTopic();
        return CommonResult.success(topicList);
    }


    /**
     * 话题详情查询 改
     * @param 话题id
     * @return
     */
    @ApiOperation("话题详情查询")
    @RequestMapping(value = "/selectTopicById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TopicBean>> queryTopicById ( @RequestParam Integer id){
        List<TopicBean> topicList = topicService.queryTopicById(id);
        return CommonResult.success(topicList);
    }


    /**
     * 话题下最新动态查询，按照发布时间排序 改
     * @param 话题id
     * @return
     */
    @ApiOperation("话题下最新动态分页查询")
    @RequestMapping(value = "/selectTopicByDynamicTime",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicBean>> queryTopicByDynamicTime( @RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize,
                                                                        //话题ID
                                                                        @RequestParam Integer id
    ){
        List<DynamicBean> dynamicList = dynamicService.queryTopicByDynamicTime(id,pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }

    /**
     * 话题下最新动态查询，按照点赞数排序,点赞数一样的按照时间先后排序 改
     * @param 话题id
     * @return
     */
    @ApiOperation("话题下最热动态分页查询")
    @RequestMapping(value = "/selectTopicByDynamicHeat",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicBean>> selectTopicByDynamicHeat( @RequestParam Integer pageNum,
                                                                         @RequestParam Integer pageSize,
                                                                         //话题ID
                                                                         @RequestParam Integer id
    ){
        List<DynamicBean> dynamicList = dynamicService.selectTopicByDynamicHeat(id,pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }

    /**
     * 根据话题名称模糊查询，新增动态时选择话题页面可查询并选择话题
     * @param topicName
     * @return
     */
    @ApiOperation("话题名称模糊查询")
    @RequestMapping(value = "/selectTopicName",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<TopicBean>> queryTopicName(String topicName) {
        List<TopicBean> topicList = topicService.queryTopicName(topicName);
        return CommonResult.success(topicList);
    }

    /**
     * 店铺下最新动态查询，按照发布时间排序
     * @param id
     * @return
     */
    @ApiOperation("店铺下最新动态分页查询")
    @RequestMapping(value = "/queryShopByDynamicTime",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicBean>> queryShopByDynamicTime(  @RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize,
                                                                        //店铺ID
                                                                        @RequestParam Long id
    ){
        List<DynamicBean> dynamicList = dynamicService.queryShopByDynamicTime(id,pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }

    /**
     * 店铺下最热动态查询，按照点赞量倒叙排序
     * @param id
     * @return
     */
    @ApiOperation("店铺下最热动态分页查询")
    @RequestMapping(value = "/queryShopByDynamicHeat",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicBean>> queryShopByDynamicHeat(  @RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize,
                                                                        //店铺ID
                                                                        @RequestParam Long id
    ){
        List<DynamicBean> dynamicList = dynamicService.queryShopByDynamicHeat(id,pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }

    /**
     * 店铺动态下动态置顶
     * @param shopId
     * @param dynamicId
     * @return
     */
    @ApiOperation("店铺动态下动态置顶")
    @RequestMapping(value = "/topDynamic",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult topDynamic(@RequestParam Long shopId,@RequestParam Long dynamicId
    ){
        Integer count = null;
        //查询当前店铺动态置顶的条数
        Integer num = dynamicService.selectTopDynamicNum(shopId);
        if (num == 3){
            return CommonResult.failed("最多置顶三条动态！");
        }else if (num == 0){
            count = num+1;
            dynamicService.topDynamic(dynamicId,count);
        }else if (num == 1){
            count = num+1;
            dynamicService.topDynamic(dynamicId,count);
        }else if (num == 2){
            count = num+1;
            dynamicService.topDynamic(dynamicId,count);
        }
        return CommonResult.success("置顶成功！");
    }

    /**
     * 店铺动态下动态取消置顶
     * @param shopId
     * @param dynamicId
     * @return
     */
    @ApiOperation("店铺动态下动态取消置顶")
    @RequestMapping(value = "/cancelTopDynamic",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult cancelTopDynamic(@RequestParam Long shopId,@RequestParam Long dynamicId
    ){
        //查询当前动态置顶等级
        Integer num = dynamicService.cancelTopDynamicNum(dynamicId);
        //如果等级为一级找出前两级number减一
        if (num == 1){
            dynamicService.updateTopDynamicNum(shopId);
            //取消置顶
            dynamicService.cancelTopDynamic(dynamicId);
        }else if(num == 2){
            dynamicService.updateTopDynamicNum2(shopId);
            dynamicService.cancelTopDynamic(dynamicId);
        }else if(num == 3){
            dynamicService.cancelTopDynamic(dynamicId);
        }
        return CommonResult.success("取消置顶成功！");
    }
}
