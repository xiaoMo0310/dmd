package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.service.PmsCourseProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: PowerNotesController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/1410:26
 */
@Controller
@Api(tags = "PowerNotesController", description = "日程安排")
@RequestMapping("/powerNotes")
public class PowerNotesController {

    @Autowired
    private PmsCourseProductService pmsCourseProductService;


    /**
     * 教练日程明细查询
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @ApiOperation("教练日程明细查询")
    @RequestMapping(value = "/selectPowerNotes", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo<PmsCourseProduct>> queryPowerNotesPage(@RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize,
                                                                        @RequestParam Long userId,
                                                                        PmsCourseProduct pmsCourseProduct) {
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
        return CommonResult.success(new PageInfo<>(powerNotesList));
    }

    /**
     * 查询报名人数
     * @param id
     * @param userId
     * @return
     */
    @ApiOperation("查询报名人数")
    @RequestMapping(value = "/selectPepleNum", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryPeopleNum(@RequestParam Long id, @RequestParam Long userId) {
        Integer num = pmsCourseProductService.queryPeopleNum(id, userId);
        return CommonResult.success(num);
    }


}