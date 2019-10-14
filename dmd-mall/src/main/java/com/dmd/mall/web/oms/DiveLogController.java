package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CommentBean;
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
import java.util.Map;

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

}
