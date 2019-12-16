package com.dmd.mall.web.cms;

import com.dmd.FileUploadUtil;
import com.dmd.base.result.CommonResult;
import com.dmd.base.result.FileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenYanbing
 * @title: MyfileController
 * @projectName dmd-master
 * @description:
 * @date 2019/10/813:06
 */

@Controller
@Api(tags = "MyFileController", description = "视频与图片上传",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/uploadFile")
public class MyFileController {

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @ApiOperation("视频上传")
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult uploadFile(@RequestParam("fileName") MultipartFile file) throws IOException {
        FileResult fileResult = fileUploadUtil.saveFile(file, "video", false);
        return CommonResult.success(fileResult.getServerPath());
    }

    @ApiOperation("图片上传")
    @PostMapping(value="/saveFile")
    @ResponseBody
    public CommonResult filesUpload(@RequestParam("files") MultipartFile[] files) {
        List<FileResult> fileResults = fileUploadUtil.saveFiles(files, "image", true);
        List<String> urlList = fileResults.stream().map(fileResult -> fileResult.getServerPath()).collect(Collectors.toList());
        String newStr = StringUtils.join(urlList, ",");
        return CommonResult.success(newStr);
    }

}