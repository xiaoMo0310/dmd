package com.dmd.admin.web;

import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.CertificateAppBean;
import com.dmd.admin.model.domain.DiveCertificateBean;
import com.dmd.admin.model.domain.UmsIntegrationChangeHistory;
import com.dmd.admin.service.DiveCertificateService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
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
     * @param certificateAppBean
     * @return
     */
    @ApiOperation("查询用户潜水证书")
    @RequestMapping(value = "/selectDiveCertificate",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertificateAppBean>> queryDiveCertificate(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                             CertificateAppBean certificateAppBean
    )  {
        if(certificateAppBean.getStratTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(certificateAppBean.getStratTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                certificateAppBean.setStratTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(certificateAppBean.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(certificateAppBean.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                certificateAppBean.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<CertificateAppBean> list = diveCertificateService.queryDiveCertificate(pageNum,pageSize,certificateAppBean);
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 批量审核通过
     * @param ids
     * @return
     */
    @OperationLog(content = "证书审核通过")
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
    @OperationLog(content = "证书审核不通过")
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


    /**
     * 审核不通过
     * @param id
     * @param pictures
     * @param userId
     * @param certificateId
     * @param createTime
     * @param operator
     * @param reason
     * @return
     */
    @OperationLog(content = "证书审核不通过")
    @ApiOperation("审核不通过")
    @RequestMapping(value = "/updateCertificateStatusNoPass",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCertificateStatusNoPass(@RequestParam Long id,
                                                      @RequestParam String pictures,
                                                      @RequestParam Long userId,
                                                      @RequestParam Integer certificateId,
                                                      @RequestParam Date createTime,
                                                      @RequestParam String operator,
                                                      @RequestParam String reason,
                                                      @RequestParam Integer userType

    ) {

        CertificateAppBean certificateAppBean = new CertificateAppBean();
        certificateAppBean.setId(id);
        certificateAppBean.setPictures(pictures);
        certificateAppBean.setUserId(userId);
        certificateAppBean.setStatus(2);
        certificateAppBean.setCertificateId(certificateId);
        certificateAppBean.setCreateTime(createTime);
        certificateAppBean.setAdopTime(null);
        certificateAppBean.setOperator(operator);
        certificateAppBean.setReason(reason);
        certificateAppBean.setUserType(userType);
        int count = diveCertificateService.updateCertificateStatusNoPass(certificateAppBean);
        if (count > 0) {
            return CommonResult.success(count,"审核不通过成功!");
        }
        return CommonResult.failed("审核不通过失败!");
    }



    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}
