package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.service.DynamicService;
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
 * @title: DynamicController
 * @projectName dmd-master
 * @description: TODO 我的动态
 * @date 2019/9/239:18
 */
@Controller
@Api(tags = "DynamicController", description = "我的动态")
@RequestMapping("/dynamic")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;

    /**
     * 查询全部我的动态
     * @param userId
     * @return
     */
    @ApiOperation("查询我的动态")
    @RequestMapping(value = "/selectDynamicAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DynamicBean>> queryDynamic(@RequestParam Long userId) {
        List<DynamicBean> dynamicList = dynamicService.queryDynamic(userId);
        return CommonResult.success(dynamicList);
    }

    /**
     * 分页查询我的动态
     * @param userId
     * @return
     */
    @ApiOperation("分页查询我的动态")
    @RequestMapping(value = "/selectDynamicPage",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DynamicBean>> queryDynamicPage(@RequestParam Long userId,
                                                            @RequestParam Integer pageNum,
                                                            @RequestParam Integer pageSize) {
        List<DynamicBean> dynamicList = dynamicService.queryDynamicPage(userId,pageNum,pageSize);
        return CommonResult.success(dynamicList);
    }

    /**
     * 查询点赞数
     * @param 动态id
     * @return
     */
    @ApiOperation("查询我的动态点赞数")
    @RequestMapping(value = "/selectDynamicPraise",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryPraise(@RequestParam Long id){
        Integer praiseNum = dynamicService.queryPraise(id);
        return CommonResult.success(praiseNum);
    }

    /**
     * 查询评论数（未完成）
     * @param 动态id
     * @return
     */
    @ApiOperation("查询我的动态评论数")
    @RequestMapping(value = "/selectDynamicrComment",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryComment(@RequestParam Long id){
        Integer commentNum = dynamicService.queryComment(id);
        return CommonResult.success(commentNum);
    }

    /**
     * 查询分享数
     * @param 动态id
     * @return
     */
    @ApiOperation("查询我的动态分享数")
    @RequestMapping(value = "/selectDynamicShare",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryShare(@RequestParam Long id){
        Integer shareNum = dynamicService.queryShare(id);
        return CommonResult.success(shareNum);
    }

    /**
     * 评论数+1,发表留言或者回复时,先修改操作动态表,动态表评论数加一,删除动态时留言回复数据表全部删除。(删除动态时也要注意话题表中动态数相应减一) 删除留言时，先删除回复再删除留言,删除回复执行修改操作评论数减一,再删除留言,评论数再减一。
     */



    /**
     * 点赞(喜欢)
     * @param 动态id
     * @return
     */
    @ApiOperation("点赞数(喜欢)")
    @RequestMapping(value = "/updateDynamicrLikePraise",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateLikePraise(@RequestParam Long id){
        int count = dynamicService.updateLikePraise(id);
        if (count > 0) {
            return CommonResult.success(count,"点赞成功");
        }
        return CommonResult.failed("点赞失败");
    }

    /**
     * 点赞(不喜欢)
     * @param 动态id
     * @return
     */
    @ApiOperation("点赞数(不喜欢)")
    @RequestMapping(value = "/updateDynamicrCancelPraise",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCancelPraise(@RequestParam Long id){
        int count = dynamicService.updateCancelPraise(id);
        if (count > 0) {
            return CommonResult.success(count,"取消点赞成功");
        }
        return CommonResult.failed("取消点赞失败");
    }


    /**
     * 分享数+1
     * @param 动态id
     * @return
     */
    @ApiOperation("分享数+1")
    @RequestMapping(value = "/updateDynamicrShare",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDynamicrShare(@RequestParam Long id){
        int count = dynamicService.updateDynamicrShare(id);
        if (count > 0) {
            return CommonResult.success(count,"分享成功");
        }
        return CommonResult.failed("分享失败");
    }
}
