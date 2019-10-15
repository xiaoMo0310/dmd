package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DiveLogAirbottleBean;
import com.dmd.mall.model.domain.DiveLogAndAirbottle;
import com.dmd.mall.model.domain.DiveLogBean;
import com.dmd.mall.service.DiveLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * @author ChenYanbing
 * @title: DiveLogController
 * @projectName dmd-masters
 * @description: TODO 我的日志
 * @date 2019/10/129:09
 */
@Controller
@Api(tags = "DiveLogController", description = "我的日志")
@RequestMapping("/diveLog")
public class DiveLogController {


    @Autowired
    private DiveLogService diveLogService;

    /**
     * 查询我的日志
     * @param userId
     * @return
     */
    @ApiOperation("查询我的日志")
    @RequestMapping(value = "/selectDiveLogAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DiveLogBean>> queryDiveLogAll(@RequestParam Long userId
                                                           //token获取
                                                           //HttpServletRequest request
       //获取用户id
       //String token = request.getHeader("token");
       //token配置类获取用户token
       //JWTResult result = JWTUtils.checkToken(token);
       //获取登陆id
       //Long userId = result.getUserId();

    ) {
        List<DiveLogBean> diveLogAllList = diveLogService.queryDiveLogAll(userId);
        return CommonResult.success(diveLogAllList);
    }


    /**
     * 根据日志id查询日志详情
     * @param id
     * @return
     */
    @ApiOperation("查询日志详情")
    @RequestMapping(value = "/selectDiveLogById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DiveLogBean>> queryDiveLogById(@RequestParam Long id){
        List<DiveLogBean> diveLogList = diveLogService.queryDiveLogById(id);
        return CommonResult.success(diveLogList);
    }


    /**
     * 根据日志id查询气瓶消耗
     * @param id
     * @return
     */
    @ApiOperation("查询日志气瓶消耗")
    @RequestMapping(value = "/selectDiveLogAirbottleByDiveLogId",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DiveLogAirbottleBean>> queryDiveLogAirbottleByDiveLogId(@RequestParam Long id){
        List<DiveLogAirbottleBean> diveLogList = diveLogService.queryDiveLogAirbottleByDiveLogId(id);
        return CommonResult.success(diveLogList);
    }

    /**
     * 新增潜水日志
     * @param diveLogAndAirbottle
     * @return
     */
    @ApiOperation("新增潜水日志")
    @RequestMapping(value = "/addDiveLog",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addDiveLog(@RequestBody DiveLogAndAirbottle diveLogAndAirbottle
                                   //token获取ID
                                   //HttpServletRequest request
                                   //前台传用户id
                                   //String userId
    ) {
        //潜水日志主表实体
        //DiveLogBean diveLogBean = diveLogAndAirbottle.getDiveLogBean();
        //潜水气瓶表List实体
        //List<DiveLogAirbottleBean> diveLogAirbottleList = diveLogAndAirbottle.getDiveLogAirbottleList();

        //获取用户id
        //String token = request.getHeader("token");
        //token配置类获取用户token
        //JWTResult result = JWTUtils.checkToken(token);
        //获取登陆id
        //Long userId = result.getUserId();

        int count = diveLogService.addDiveLog(diveLogAndAirbottle);
        if (count > 0) {
            return CommonResult.success(count,"日志发布成功");
        }
        return CommonResult.failed("日志发布失败");
    }

    /**
     * 日志点赞
     * @param id
     * @return
     */
    @ApiOperation("点赞日志")
    @RequestMapping(value = "/updateDiveLogLikePraise",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateLikePraise(@RequestParam Long id){
        int count = diveLogService.updateLikePraise(id);
        if (count > 0) {
            return CommonResult.success(count,"点赞成功");
        }
        return CommonResult.failed("点赞失败");
    }

    /**
     * 取消点赞
     * @param id
     * @return
     */
    @ApiOperation("取消点赞日志")
    @RequestMapping(value = "/updateDiveLogCancelPraise",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCancelPraise(@RequestParam Long id){
        int count = diveLogService.updateCancelPraise(id);
        if (count > 0) {
            return CommonResult.success(count,"取消点赞成功");
        }
        return CommonResult.failed("取消点赞失败");
    }

    /**
     * 查询日志点赞数
     * @param id
     * @return
     */
    @ApiOperation("查询日志点赞数")
    @RequestMapping(value = "/selectDiveLogPraise",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryDiveLogPraise(@RequestParam Long id){
        Integer praiseNum = diveLogService.queryPraise(id);
        return CommonResult.success(praiseNum);
    }

    /**
     * 日志分享
     * @param id
     * @return
     */
    @ApiOperation("分享数+1")
    @RequestMapping(value = "/updateDiveLogShare",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDiveLogShare(@RequestParam Long id){
        int count = diveLogService.updateDiveLogShare(id);
        if (count > 0) {
            return CommonResult.success(count,"分享成功");
        }
        return CommonResult.failed("分享失败");
    }

    /**
     * 查询日志分享数
     * @param id
     * @return
     */
    @ApiOperation("查询日志分享数")
    @RequestMapping(value = "/selectDiveLogShare",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryDiveLogShare(@RequestParam Long id){
        Integer praiseNum = diveLogService.queryShare(id);
        return CommonResult.success(praiseNum);
    }

    /**
     * 潜水日志逻辑删除
     * @param id
     * @return
     */
    @ApiOperation("潜水日志逻辑删除")
    @RequestMapping(value = "/updateDiveLogDelflag",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDiveLogDelflag(@RequestParam Long id){
        int count = diveLogService.updateDiveLogDelflag(id);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
