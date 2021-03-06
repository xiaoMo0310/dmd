package com.dmd.admin.web;

import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.*;
import com.dmd.admin.service.IntegralAdminService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
 * @title: IntegralAdminController
 * @projectName dmd-masters
 * @description: TODO 后台--积分管理
 * @date 2019/10/2214:18
 */
@Controller
@Api(tags = "IntegralAdminController", description = "后台--积分管理")
@RequestMapping("/integral")
public class IntegralAdminController {

    @Autowired
    private IntegralAdminService integralAdminService;

    /**
     * 积分规则说明查询
     * @return
     */
    @ApiOperation("查询积分规则说明")
    @RequestMapping(value = "/selectIntegralRule",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<IntegralRuleBean>> queryIntegralRule(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<IntegralRuleBean> List = integralAdminService.queryIntegralRule();
        return CommonResult.success(CommonPage.restPage(List));
    }

    /**
     * 积分规则修改
     * @param integralRuleBean
     * @return
     */
    @OperationLog(content = "修改积分规则说明")
    @ApiOperation("修改积分规则说明")
    @RequestMapping(value = "/updateIntegralRule",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralRule(@RequestBody IntegralRuleBean integralRuleBean) {
        int count = integralAdminService.updateIntegralRule(integralRuleBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");

    }

    /**
     * 回显积分规则
     * @param id
     * @return
     */
    @ApiOperation("回显积分规则")
    @RequestMapping(value = "/findIntegralInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntegralRuleBean> findIntegralInfoById(@RequestParam Long id){
        IntegralRuleBean integralRuleBean = integralAdminService.findIntegralInfoById(id);
        return CommonResult.success(integralRuleBean);
    }


    /**
     * 查询积分明细记录
     * @param pageNum
     * @param pageSize
     * @param umsIntegrationChangeHistory
     * @return
     */
    @ApiOperation("查询积分明细记录")
    @RequestMapping(value = "/selectIntegralChange",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsIntegrationChangeHistory>> queryIntegralChangePage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                                         UmsIntegrationChangeHistory umsIntegrationChangeHistory
    ) {

        if(umsIntegrationChangeHistory.getStartTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsIntegrationChangeHistory.getStartTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsIntegrationChangeHistory.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsIntegrationChangeHistory.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsIntegrationChangeHistory.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsIntegrationChangeHistory.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<UmsIntegrationChangeHistory> List = integralAdminService.queryIntegralChangePage(pageNum,pageSize,umsIntegrationChangeHistory);
        return CommonResult.success(CommonPage.restPage(List));
    }


    /**
     * 增加用户积分操作
     * @param id
     * @param changeCount
     * @param operateMan
     * @param operateNote
     * @param integralTrend
     * @param memberId
     * @return
     */
    @OperationLog(content = "增加用户积分操作")
    @ApiOperation("增加用户积分操作")
    @RequestMapping(value = "/addIntegration",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegration(@RequestParam Long id,
                                          @RequestParam Integer changeCount,
                                          @RequestParam String operateMan,
                                          @RequestParam String operateNote,
                                          @RequestParam String integralTrend,
                                          @RequestParam Long memberId) {
        UmsIntegrationChangeHistory umsIntegrationChangeHistory = new UmsIntegrationChangeHistory();
        umsIntegrationChangeHistory.setId(id);
        umsIntegrationChangeHistory.setChangeCount(changeCount);
        umsIntegrationChangeHistory.setOperateNote(operateNote);
        umsIntegrationChangeHistory.setIntegralTrend(integralTrend);
        umsIntegrationChangeHistory.setMemberId(memberId);
        umsIntegrationChangeHistory.setOperateMan(operateMan);
        int count = integralAdminService.updateIntegration(umsIntegrationChangeHistory);
        if (count > 0) {
            return CommonResult.success(count,"增加成功");
        }
        return CommonResult.failed("增加失败");
    }

    /**
     * 减少用户积分操作
     * @param id
     * @param changeCount
     * @param operateMan
     * @param operateNote
     * @param integralTrend
     * @param memberId
     * @return
     */
    @OperationLog(content = "减少用户积分操作")
    @ApiOperation("减少用户积分操作")
    @RequestMapping(value = "/reduceIntegration",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegrationReduce(@RequestParam Long id,
                                                @RequestParam Integer changeCount,
                                                @RequestParam String operateMan,
                                                @RequestParam String operateNote,
                                                @RequestParam String integralTrend,
                                                @RequestParam Long memberId) {
        UmsIntegrationChangeHistory umsIntegrationChangeHistory = new UmsIntegrationChangeHistory();
        umsIntegrationChangeHistory.setId(id);
        umsIntegrationChangeHistory.setChangeCount(changeCount);
        umsIntegrationChangeHistory.setOperateNote(operateNote);
        umsIntegrationChangeHistory.setIntegralTrend(integralTrend);
        umsIntegrationChangeHistory.setMemberId(memberId);
        umsIntegrationChangeHistory.setOperateMan(operateMan);
        int count = integralAdminService.updateIntegrationReduce(umsIntegrationChangeHistory);
        if (count > 0) {
            return CommonResult.success(count,"减少成功");
        }
        //查询用户总积分
        Integer num = integralAdminService.queryMemberNum(memberId);
        return CommonResult.failed("减少失败,减少的数额超过用户总积分！用户总积分为"+num+"请核实后在操作！");
    }

    /**
     * 查询全部积分好礼
     * @param pageNum
     * @param pageSize
     * @param integralGiftsBean
     * @return
     */
    @ApiOperation("查询全部积分好礼")
    @RequestMapping(value = "/selectIntegralGifts",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<IntegralGiftsBean>> queryIntegralGifts(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                          IntegralGiftsBean integralGiftsBean) {
        if(integralGiftsBean.getStratTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(integralGiftsBean.getStratTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                integralGiftsBean.setStratTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(integralGiftsBean.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(integralGiftsBean.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                integralGiftsBean.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<IntegralGiftsBean> integralGiftsList = integralAdminService.queryIntegralGifts(pageNum,pageSize,integralGiftsBean);
        return CommonResult.success(CommonPage.restPage(integralGiftsList));
    }


    /**
     * 修改积分好礼
     * @param id
     * @param integralGiftsBean
     * @return
     */
    @OperationLog(content = "修改积分好礼")
    @ApiOperation("修改积分好礼")
    @RequestMapping(value = "/updateIntegralGifts",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralGifts(@RequestParam Long id,@RequestBody IntegralGiftsBean integralGiftsBean) {
        integralGiftsBean.setId(id);
        int count = integralAdminService.updateIntegralGiftsById(integralGiftsBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }

    /**
     * 回显积分好礼
     * @param id
     * @return
     */
    @ApiOperation("回显积分好礼")
    @RequestMapping(value = "/findIntegralGiftsInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntegralGiftsBean> findIntegralGiftsInfoById(@RequestParam Long id){
        IntegralGiftsBean integralGiftsBean = integralAdminService.findIntegralGiftsInfoById(id);
        return CommonResult.success(integralGiftsBean);
    }

    /**
     * 添加积分好礼
     * @param integralGiftsBean
     * @return
     */
    @ApiOperation("添加积分好礼")
    @RequestMapping(value = "/addIntegralGifts",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addTopic(@RequestBody IntegralGiftsBean integralGiftsBean) {
        int count = integralAdminService.addIntegralGifts(integralGiftsBean);
        if (count > 0) {
            return CommonResult.success(count,"添加成功");
        }
        return CommonResult.failed("添加失败");
    }

    /**
     * 批量删除积分好礼
     * @param ids
     * @return
     */
    @OperationLog(content = "删除积分好礼")
    @ApiOperation("批量删除积分好礼")
    @RequestMapping(value = "/deleteIntegralGiftsById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteIntegralGiftsById(@RequestParam("ids") List<Long> ids){
        int count = integralAdminService.deleteIntegralGiftsById(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 添加积分好礼规格
     * @param list
     * @return
     */
    @ApiOperation("添加积分好礼规格")
    @RequestMapping(value = "/addIntegralGiftsSpe",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addIntegralGiftsSpe(@RequestParam("id")Long id,@RequestBody List<IntegralGiftsSpeBean> list) {

        //循环赋值
        for (IntegralGiftsSpeBean aList : list) {
            aList.setGiftId(id);
        }
            System.out.println(list);
        int count = integralAdminService.addIntegralGiftsSpe(list);
        if (count > 0) {
            return CommonResult.success(count,"添加成功");
        }
        return CommonResult.failed("添加失败");
    }

    /**
     * 添加积分好礼规格
     * @param giftId
     * @param specStock
     * @param size
     * @param color
     * @return
     */
    @ApiOperation("添加积分好礼规格")
    @RequestMapping(value = "/addGiftsSpe",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addGiftsSpe(@RequestParam Long giftId,
                                    @RequestParam Integer specStock,
                                    @RequestParam String size,
                                    @RequestParam String color
                                    ) {
        IntegralGiftsSpeBean integralGiftsSpeBean = new IntegralGiftsSpeBean();
        integralGiftsSpeBean.setGiftId(giftId);
        integralGiftsSpeBean.setSpecStock(specStock);
        integralGiftsSpeBean.setSize(size);
        integralGiftsSpeBean.setColor(color);
        int count = integralAdminService.addGiftsSpe(integralGiftsSpeBean);
        if (count > 0) {
            return CommonResult.success(count,"增加成功");
        }
        return CommonResult.failed("增加失败");
    }
    /**
     * 查询产品的规格
     * @param id
     * @return
     */
    @ApiOperation("查询礼品的规格")
    @RequestMapping(value = "/selectIntegralGiftsSpeById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<IntegralGiftsSpeBean>> queryIntegralGiftsSpeById(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "1000") Integer pageSize,
            @RequestParam Long id) {
        List<IntegralGiftsSpeBean> integralGiftsSpeList = integralAdminService.queryIntegralGiftsSpeById(id);
        return CommonResult.success(CommonPage.restPage(integralGiftsSpeList));
    }



    /**
     * 礼品批量上架
     * @param ids
     * @return
     */
    @OperationLog(content = "礼品上架")
    @ApiOperation("礼品批量上架")
    @RequestMapping(value = "/updateIntegralGiftsPass",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralGiftsPass(@RequestParam("ids") List<Long> ids){
        int count = integralAdminService.updateIntegralGiftsPass(ids);
        if (count > 0) {
            return CommonResult.success(count,"上架成功");
        }
        return CommonResult.failed("上架失败");
    }

    /**
     * 礼品批量下架
     * @param ids
     * @return
     */
    @OperationLog(content = "礼品下架")
    @ApiOperation("礼品批量下架")
    @RequestMapping(value = "/updateIntegralGiftsNoPass",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralGiftsNoPass(@RequestParam("ids") List<Long> ids){
        int count = integralAdminService.updateIntegralGiftsNoPass(ids);
        if (count > 0) {
            return CommonResult.success(count,"下架成功");
        }
        return CommonResult.failed("下架失败");
    }

    /**
     * 修改礼品规格
     * @param id
     * @param integralGiftsSpeBean
     * @return
     */
    @OperationLog(content = "修改礼品规格")
    @ApiOperation("修改礼品规格")
    @RequestMapping(value = "/updateIntegralGiftsSpe",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralGiftsSpe(@RequestParam Long id,@RequestBody IntegralGiftsSpeBean integralGiftsSpeBean) {
        integralGiftsSpeBean.setId(id);
        int count = integralAdminService.updateIntegralGiftsSpe(integralGiftsSpeBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");
    }

    /**
     * 回显好礼规格
     * @param id
     * @return
     */
    @ApiOperation("回显好礼规格")
    @RequestMapping(value = "/findIntegralGiftsSpeInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntegralGiftsSpeBean> findIntegralGiftsSpeInfoById(@RequestParam Long id){
        IntegralGiftsSpeBean integralGiftsBean = integralAdminService.findIntegralGiftsSpeInfoById(id);
        return CommonResult.success(integralGiftsBean);
    }

    /**
     * 批量删除礼品规格
     * @param ids
     * @return
     */
    @OperationLog(content = "删除礼品规格")
    @ApiOperation("批量删除礼品规格")
    @RequestMapping(value = "/deleteIntegralGiftsSpeById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteIntegralGiftsSpeById(@RequestParam("ids") List<Long> ids){
        int count = integralAdminService.deleteIntegralGiftsSpeById(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }


    /**
     * 查询积分明细记录
     * @param pageNum
     * @param pageSize
     * @param umsIntegrationChangeHistory
     * @return
     */
    @ApiOperation("查询教练积分明细记录")
    @RequestMapping(value = "/selectIntegralChangeByCoach",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsIntegrationChangeHistory>> queryIntegralChangeByCoach(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                                         UmsIntegrationChangeHistory umsIntegrationChangeHistory
    ) {

        if(umsIntegrationChangeHistory.getStartTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsIntegrationChangeHistory.getStartTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsIntegrationChangeHistory.setStartTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(umsIntegrationChangeHistory.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(umsIntegrationChangeHistory.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                umsIntegrationChangeHistory.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<UmsIntegrationChangeHistory> List = integralAdminService.queryIntegralChangeByCoach(pageNum,pageSize,umsIntegrationChangeHistory);
        return CommonResult.success(CommonPage.restPage(List));
    }

    /**
     * 增加教练积分操作
     * @param id
     * @param changeCount
     * @param operateMan
     * @param operateNote
     * @param memberId
     * @return
     */
    @OperationLog(content = "增加教练积分操作")
    @ApiOperation("增加教练积分操作")
    @RequestMapping(value = "/addIntegrationCoach",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegrationCoach(@RequestParam Long id,
                                          @RequestParam Integer changeCount,
                                          @RequestParam String operateMan,
                                          @RequestParam String operateNote,
                                          /*@RequestParam String integralTrend,*/
                                          @RequestParam Long memberId) {
        UmsIntegrationChangeHistory umsIntegrationChangeHistory = new UmsIntegrationChangeHistory();
        umsIntegrationChangeHistory.setId(id);
        umsIntegrationChangeHistory.setChangeCount(changeCount);
        umsIntegrationChangeHistory.setOperateNote(operateNote);
        /*umsIntegrationChangeHistory.setIntegralTrend(integralTrend);*/
        umsIntegrationChangeHistory.setMemberId(memberId);
        umsIntegrationChangeHistory.setOperateMan(operateMan);
        int count = integralAdminService.updateIntegrationCoach(umsIntegrationChangeHistory);
        if (count > 0) {
            return CommonResult.success(count,"增加成功");
        }
        return CommonResult.failed("增加失败");
    }

    /**
     * 减少教练积分操作
     * @param id
     * @param changeCount
     * @param operateMan
     * @param operateNote
     * @param memberId
     * @return
     */
    @OperationLog(content = "减少教练积分操作")
    @ApiOperation("减少教练积分操作")
    @RequestMapping(value = "/reduceIntegrationCoach",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegrationReduceCoach(@RequestParam Long id,
                                                @RequestParam Integer changeCount,
                                                @RequestParam String operateMan,
                                                @RequestParam String operateNote,
                                                /*@RequestParam String integralTrend,*/
                                                @RequestParam Long memberId) {
        UmsIntegrationChangeHistory umsIntegrationChangeHistory = new UmsIntegrationChangeHistory();
        umsIntegrationChangeHistory.setId(id);
        umsIntegrationChangeHistory.setChangeCount(changeCount);
        umsIntegrationChangeHistory.setOperateNote(operateNote);
        /*umsIntegrationChangeHistory.setIntegralTrend(integralTrend);*/
        umsIntegrationChangeHistory.setMemberId(memberId);
        umsIntegrationChangeHistory.setOperateMan(operateMan);
        int count = integralAdminService.updateIntegrationReduceCoach(umsIntegrationChangeHistory);
        if (count > 0) {
            return CommonResult.success(count,"减少成功");
        }
        //查询教练总积分
        Integer num = integralAdminService.queryMemberNumCoach(memberId);
        return CommonResult.failed("减少失败,减少的数额超过用户总积分！用户总积分为"+num+"请核实后在操作！");
    }

}
