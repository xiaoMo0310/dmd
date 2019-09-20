package com.dmd.mall.web.oms;

import com.dmd.FileUtil;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ChenYanbing
 * @title: CoachUploadController
 * @projectName dmd-master
 * @description: TODO 教练申请图片上传
 * @date 2019/9/1914:28
 */
@Controller
@Api(tags = "CoachUploadController", description = "图片上传")
@RequestMapping("/upload")
public class CoachUploadController {

    @ApiOperation("教练申请审核图片上传身份证资格证书")
    @RequestMapping(value="/picture", method= RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(MultipartFile img, HttpServletRequest request) {
        String fileUpload = FileUtil.FileUpload(img, request);
        return CommonResult.success(fileUpload);
    }
}
