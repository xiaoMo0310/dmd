package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
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
     * 查询评论数
     * @param 动态id
     * @return
     */
    @ApiOperation("查询我的动态评论数")
    @RequestMapping(value = "/selectDynamicComment",method = RequestMethod.GET)
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
    @RequestMapping(value = "/updateDynamicLikePraise",method = RequestMethod.POST)
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
    @RequestMapping(value = "/updateDynamicCancelPraise",method = RequestMethod.POST)
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
    @RequestMapping(value = "/updateDynamicShare",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDynamicrShare(@RequestParam Long id){
        int count = dynamicService.updateDynamicrShare(id);
        if (count > 0) {
            return CommonResult.success(count,"分享成功");
        }
        return CommonResult.failed("分享失败");
    }

    /**
     * 动态删除(逻辑删除)
     * @param 动态id
     * @return
     */
    @ApiOperation("动态删除")
    @RequestMapping(value = "/updateDynamicDelflag",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDynamicDelflag(@RequestParam Long id){
        int count = dynamicService.updateDynamicDelflag(id);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 根据动态id查询动态详情
     * @param 话题id
     * @return
     */
    @ApiOperation("根据动态id查询动态详情")
    @RequestMapping(value = "/selectDynamicById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DynamicBean>> queryDynamicById(@RequestParam Long id) {
        List<DynamicBean> dynamicList = dynamicService.queryDynamicById(id);
        return CommonResult.success(dynamicList);
    }

    /**
     * 发布动态
     * @param dynamicBean
     * @return
     */
    @ApiOperation("发布动态")
    @RequestMapping(value = "/addDynamic",method = RequestMethod.POST)
    @ResponseBody                                                       //token获取用户id
    public CommonResult addDynamic(@RequestBody DynamicBean dynamicBean //HttpServletRequest request
                                                                        //前台传用户id
                                                                        //String userId
        ){

        //获取用户id
        //String token = request.getHeader("token");
        //token配置类获取用户token
        //JWTResult result = JWTUtils.checkToken(token);
        //获取登陆id
        //Long userId = result.getUserId();
        //关联用户ID
        dynamicBean.setUserId((long)(1));
        int count = dynamicService.addDynamic(dynamicBean);
        if (count > 0) {
            return CommonResult.success(count,"发布成功");
        }
        return CommonResult.failed("发布失败");
    }
}
