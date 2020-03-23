package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CertificateAppBean;
import com.dmd.mall.model.domain.PmsCertificate;
import com.dmd.mall.service.DiveCertificateServuce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateController
 * @projectName dmd-masters
 * @description:
 * @date 2019/11/413:25
 */
@Controller
@Api(tags = "DiveCertificateController", description = "我的--我的证书")
@RequestMapping("/diveCertificate")
public class DiveCertificateController {

    @Autowired
    private DiveCertificateServuce diveCertificateServuce;

    /**
     * 查询我的证书
     * @return
     *//*
    @ApiOperation("查询我的证书")
    @RequestMapping(value = "/selectDiveCertificate",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<DiveCertificateBean> queryDiveCertificate(Long userId) {
        DiveCertificateBean list = diveCertificateServuce.queryDiveCertificate(userId);
        return CommonResult.success(list);
    }*/

    @ApiOperation("上传我的证书")
    @RequestMapping(value = "/addDiveCertificate",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addDiveCertificate(@RequestBody CertificateAppBean certificateAppBean) {
        int count = diveCertificateServuce.addDiveCertificate(certificateAppBean);
        if (count > 0) {
            return CommonResult.success(count,"上传成功,请等待审核!");
        }
       // return CommonResult.failed("请先上传上一等级证书或等待上一等级证书审核通过!");
        return CommonResult.failed("上传失败！");

    }

    /**
     * 查询我的证书
     */
    @ApiOperation("查询我的证书")
    @RequestMapping(value = "/selectDiveCertificate",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertificateAppBean>> queryDiveCertificate(Long userId) {
        List<CertificateAppBean> list = diveCertificateServuce.queryDiveCertificate(userId);
        return CommonResult.success(list);
    }

    /**
     * 查询教练级别说明
     */
    @ApiOperation("查询教练级别说明")
    @RequestMapping(value = "/selectDiveCertificateRank",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsCertificate>> queryDiveCertificateRank() {
        List<PmsCertificate> list = diveCertificateServuce.queryDiveCertificateRank();
        return CommonResult.success(list);
    }
}
