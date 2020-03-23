package com.dmd.mall.web.cms;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.result.CommonResult;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.domain.PowerNotesBean;
import com.dmd.mall.model.vo.PowerNotesMemberVo;
import com.dmd.mall.service.PmsCourseProductService;
import com.dmd.mall.service.PowerNoteService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: PowerNotesController
 * @projectName dmd-masters
 * @description:
 * @date 2019/11/1410:26
 */
@Controller
@Api(tags = "PowerNotesController", description = "日程安排")
@RequestMapping("/powerNotes")
public class PowerNotesController {

    @Autowired
    private PmsCourseProductService pmsCourseProductService;

    @Autowired
    private PowerNoteService powerNoteService;

    /**
     * 教练日程明细查询 改
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     *//*
    @ApiOperation("教练日程明细查询")
    @RequestMapping(value = "/selectPowerNotes", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<PmsCourseProduct>> queryPowerNotesPage(@RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize,
                                                                        @RequestParam Long userId,
                                                                        PmsCourseProduct pmsCourseProduct) {
        List<PmsCourseProduct> objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        if (userTypes.equals("coach")){
            if (pmsCourseProduct.getSearchStartTime() != null) {
                String time = "";
                String time2 = "";
                String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(pmsCourseProduct.getSearchStartTime());
                if (StringUtils.isNotBlank(dateStr)) {
                    StringBuilder sb = new StringBuilder(dateStr);
                    StringBuilder sb2 = new StringBuilder(dateStr);
                    sb.replace(11, 19, "00:00:00");
                    sb2.replace(11, 19, "24:00:00");
                    time = sb.toString();
                    time2 = sb2.toString();
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = format.parse(time);
                    Date date2 = format.parse(time2);
                    pmsCourseProduct.setSearchStartTime(date);
                    pmsCourseProduct.setSearchEndTime(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            List<PmsCourseProduct> powerNotesList = pmsCourseProductService.queryPowerNotesPage(pageNum, pageSize, userId, pmsCourseProduct);
            objects = powerNotesList;
        }
        return CommonResult.success(new PageInfo<>(objects));
    }
*/
    /**
     * 查询报名人数 改
     * @param id
     * @param userId
     * @return
     */
    @ApiOperation("查询报名人数")
    @RequestMapping(value = "/selectPepleNum", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryPeopleNum(@RequestParam Long id, @RequestParam Long userId,
    @RequestParam Integer productType
    ) {
        int count = 0;
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登录角色
        String userTypes = loginAuthDto.getUserType();
        if (userTypes.equals("coach")){
            Integer num = pmsCourseProductService.queryPeopleNum(id, userId,productType);
            count = num;
        }
        return CommonResult.success(count);
    }

    /**
     * 教练明细查询
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param pmsCourseProduct
     * @return
     */
    @ApiOperation("教练日程明细查询")
    @RequestMapping(value = "/selectPowerNotesCoachPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<PowerNotesBean>> queryPowerNotesCoachPage(@RequestParam Integer pageNum,
                                                                           @RequestParam Integer pageSize,
                                                                           @RequestParam Long userId,
                                                                           PmsCourseProduct pmsCourseProduct) {
        List<PowerNotesBean> powerNotesList = pmsCourseProductService.queryPowerNotesCoachPage(pageNum, pageSize, userId, pmsCourseProduct);
        return CommonResult.success(new PageInfo<>(powerNotesList));
    }


    /**
     * 当月教练日程
     * @param userId
     * @return
     */
    @ApiOperation("当月教练日程")
    @RequestMapping(value = "/selectPowerNotesCoachToMonth", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PowerNotesBean>> queryPowerNotesCoachToMonth(@RequestParam Long userId) {
        List<PowerNotesBean> powerNotesList = pmsCourseProductService.queryPowerNotesCoachToMonth(userId);
        return CommonResult.success(powerNotesList);
    }

    /**
     * 教练日程详情查询
     * @param id
     * @return
     */
    @ApiOperation("教练日程详情查询")
    @RequestMapping(value = "/selectPowerNotesById", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PowerNotesBean> selectPowerNotesById(@RequestParam Long id) {
        PowerNotesBean powerNotes = powerNoteService.selectPowerNotesById(id);
        return CommonResult.success(powerNotes);
    }


    /**
     * 教练日程详情修改
     * @param powerNotesBean
     * @return
     */
    @ApiOperation("教练日程详情修改")
    @RequestMapping(value = "/updatePowerNotesById", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updatePowerNotesById(PowerNotesBean powerNotesBean) {
        int count = powerNoteService.updatePowerNotesById(powerNotesBean);
        if (count > 0) {
            return CommonResult.success(count,"保存成功");
        }
        return CommonResult.failed("保存失败");
    }

    /**
     * 教练日程新增
     * @param powerNotesBean
     * @return
     */
    @ApiOperation("教练日程新增")
    @RequestMapping(value = "/addDynamic",method = RequestMethod.POST)
    @ResponseBody                                                       //token获取用户id
    public CommonResult addDynamic(@RequestBody PowerNotesBean powerNotesBean){

        int count = powerNoteService.addPowerNote(powerNotesBean);
        if (count > 0) {
            return CommonResult.success(count,"新增成功");
        }
        return CommonResult.failed("新增失败");
    }

    /**
     * 教练日程详情涉及学员查询
     * @param userId
     * @param productId
     * @param productType
     * @return
     */
    @ApiOperation("教练日程详情涉及学员查询")
    @RequestMapping(value = "/selectPowerNotesMember", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PowerNotesMemberVo>> selectPowerNotesMember(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer productType) {
        List<PowerNotesMemberVo> powerNotes = pmsCourseProductService.selectPowerNotesMember(userId,productId,productType);
        return CommonResult.success(powerNotes);
    }



}