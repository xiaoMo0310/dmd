package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.model.domain.DiveCertificateBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.service.DiveCertificateServuce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveCertificateController
 * @projectName dmd-masters
 * @description: TODO
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
     */
    @ApiOperation("查询我的证书")
    @RequestMapping(value = "/selectDiveCertificate",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DiveCertificateBean>> queryDiveCertificate(Long userId) {
        List<DiveCertificateBean> list = diveCertificateServuce.queryDiveCertificate(userId);
        return CommonResult.success(list);
    }


    @ApiOperation("上传我的证书")
    @RequestMapping(value = "/addDiveCertificate",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addDiveCertificate(@RequestParam Long userId,
                                           @RequestBody DiveCertificateBean diveCertificateBean,
                                           @RequestParam Integer identifier) {
        int count = diveCertificateServuce.addDiveCertificate(userId,diveCertificateBean,identifier);
        if (count > 0) {
            return CommonResult.success(count,"上传成功,请等待审核!");
        }
        return CommonResult.failed("上传失败,请先上传上一等级证书!");
    }
}
