package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicAlbumTimeBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.vo.UserDetailsVo;
import com.dmd.mall.service.DynamicService;
import com.github.pagehelper.PageInfo;
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
 * @description: 我的动态
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
     * 分页查询我的动态 改
     * @param userId
     * @return
         */
        @ApiOperation("分页查询我的动态")
        @RequestMapping(value = "/selectDynamicPage",method = RequestMethod.GET)
        @ResponseBody
        public CommonResult<PageInfo<DynamicBean>> queryDynamicPage(@RequestParam Long userId,
                                                                    @RequestParam Integer pageNum,
                                                                    @RequestParam Integer pageSize,
                                                                    Integer userType) {
            List<DynamicBean> dynamicList = dynamicService.queryDynamicPage(userId,pageNum,pageSize,userType);
            return CommonResult.success(new PageInfo<>(dynamicList));
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
     * 动态点赞 改
     * @param 动态id
     * @return
     */
    @ApiOperation("点赞动态")
    @RequestMapping(value = "/updateDynamicLikePraise",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateLikePraise(@RequestParam Long id,@RequestParam Integer userType){
        int count = dynamicService.updateLikePraise(id,userType);
        if (count > 0) {
            return CommonResult.success(count,"点赞成功");
        }
        return CommonResult.failed("点赞失败");
    }

    /**
     * 动态取消点赞 改
     * @param 动态id
     * @return
     */
    @ApiOperation("取消点赞动态")
    @RequestMapping(value = "/updateDynamicCancelPraise",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateCancelPraise(@RequestParam Long id,@RequestParam Integer userType){
        int count = dynamicService.updateCancelPraise(id,userType);
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
    @RequestMapping(value = "/updateDynamicShare",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateDynamicrShare(@RequestParam Long id){
        int count = dynamicService.updateDynamicrShare(id);
        if (count > 0) {
            return CommonResult.success(count,"分享成功");
        }
        return CommonResult.failed("分享失败");
    }

    /**
     * 动态删除(逻辑删除) 改
     * @param 动态id
     * @return
     */
    @ApiOperation("动态删除")
    @RequestMapping(value = "/updateDynamicDelflag",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updateDynamicDelflag(@RequestParam Long id){
        int count = dynamicService.updateDynamicDelflag(id);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 根据动态id查询动态详情 改
     * @param 话题id
     * @return
     */
    @ApiOperation("根据动态id查询动态详情")
    @RequestMapping(value = "/selectDynamicById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DynamicBean>> queryDynamicById(@RequestParam Long id, Integer userType) {
        if (userType == null){
            userType = 0;
        }
        List<DynamicBean> dynamicList = dynamicService.queryDynamicById(id,userType);
        return CommonResult.success(dynamicList);
    }

    /**
     * 发布动态 改
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
        int count = dynamicService.addDynamic(dynamicBean);
        if (count > 0) {
            return CommonResult.success(count,"发布成功");
        }
        return CommonResult.failed("发布失败");
    }

    /**
     * 首页--最新动态 改
     * @return
     */
    @ApiOperation("首页--最新动态分页查询")
    @RequestMapping(value = "/selectDynamicTime",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicBean>> queryDynamicTime(@RequestParam Integer pageNum,
                                                                @RequestParam Integer pageSize) {
        List<DynamicBean> dynamicList = dynamicService.queryDynamicTime(pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }

    /**
     * 首页--最热动态 改
     * @return
     */
    @ApiOperation("首页--最热动态分页查询")
    @RequestMapping(value = "/selectDynamicHeat",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicBean>> queryDynamicHeat(@RequestParam Integer pageNum,
                                                                @RequestParam Integer pageSize) {
        List<DynamicBean> dynamicList = dynamicService.queryDynamicHeat(pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }

    /**
     * 查询我的总动态数 改
     * @param userId
     * @return
     */
    @ApiOperation("查询我的总动态数")
    @RequestMapping(value = "/selectDynamicCount",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryDynamicCount(@RequestParam Long userId) {
        Integer count = dynamicService.queryDynamicCount(userId);
        return CommonResult.success(count);
    }

    /**
     * 分页查询用户影集时刻 改
     * @param userId
     * @return
     */
    @ApiOperation("分页查询用户影集时刻")
    @RequestMapping(value = "/selectDynamicAlbumTimeBean",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<DynamicAlbumTimeBean>> queryDynamicAlbumTimeBean(@RequestParam Long userId,
                                                                                  @RequestParam Integer pageNum,
                                                                                  @RequestParam Integer pageSize,
                                                                                  @RequestParam Integer userType) {
        List<DynamicAlbumTimeBean> dynamicList = dynamicService.queryDynamicAlbumTimeBean(userId,pageNum,pageSize,userType);
        return CommonResult.success(new PageInfo<>(dynamicList));
    }


    /**
     * 查询用户详情页资料 改
     * @param userId
     * @return
     */
    @ApiOperation("查询用户详情页资料")
    @RequestMapping(value = "/queryUserDetails",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UserDetailsVo> queryUserDetails(@RequestParam Long userId,@RequestParam Integer userType) {
        UserDetailsVo umsMember = dynamicService.queryUserDetails(userId,userType);
        return CommonResult.success(umsMember);
    }

}
