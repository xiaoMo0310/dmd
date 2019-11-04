package com.dmd.admin.web;

import com.dmd.admin.model.domain.DiveCertificateBean;
import com.dmd.admin.service.DiveCertificateService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @title: DiveCertificateController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/11/413:25
 */
@Controller
@Api(tags = "DiveCertificateController", description = "后台--用户潜水证书管理")
@RequestMapping("/diveCertificate")
public class DiveCertificateController {


    @Autowired
    private DiveCertificateService diveCertificateService;

    /**
     * 查询用户潜水证书
     * @param pageNum
     * @param pageSize
     * @param diveCertificateBean
     * @return
     */
    @ApiOperation("查询用户潜水证书")
    @RequestMapping(value = "/selectDiveCertificate",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<DiveCertificateBean>> queryDiveCertificate(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                              DiveCertificateBean diveCertificateBean
    )  {
        if(diveCertificateBean.getStratTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(diveCertificateBean.getStratTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                diveCertificateBean.setStratTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(diveCertificateBean.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(diveCertificateBean.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                diveCertificateBean.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<DiveCertificateBean> list = diveCertificateService.queryDiveCertificate(pageNum,pageSize,diveCertificateBean);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 批量审核通过
     * @param ids
     * @return
     */
    @ApiOperation("批量审核通过")
    @RequestMapping(value = "/updateDiveCertificateStatusPass",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDiveCertificateStatusPass(@RequestParam("ids") List<Long> ids){
        int count = diveCertificateService.updateDiveCertificateStatus(ids);
        if (count > 0) {
            return CommonResult.success(count,"审核通过成功");
        }
        return CommonResult.failed("通过失败");
    }

    /**
     * 批量审核不通过
     * @param ids
     * @return
     */
    @ApiOperation("批量审核不通过")
    @RequestMapping(value = "/updateDiveCertificateStatusNoPass",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateDiveCertificateStatusNoPass(@RequestParam("ids") List<Long> ids){
        int count = diveCertificateService.updateDiveCertificateStatusNoPass(ids);
        if (count > 0) {
            return CommonResult.success(count,"审核不通过成功");
        }
        return CommonResult.failed("不通过失败");
    }

}
