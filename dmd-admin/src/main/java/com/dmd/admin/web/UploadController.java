package com.dmd.admin.web;

import com.dmd.FileUploadUtil;
import com.dmd.base.result.CommonResult;
import com.dmd.base.result.FileResult;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 17:57
 * @Description 文件上传
 * */
@RestController
@RequestMapping("/upload")
@Api(tags = "UploadController", description = "文件上传中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadController {

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @PostMapping("/file")
    @ApiOperation(httpMethod = "POST",value = "上传图片")
    public Wrapper<FileResult> uploadImage(@RequestBody MultipartFile file) throws IOException {
        FileResult fileResult = fileUploadUtil.saveFile(file, "dmd", true);
        return WrapMapper.ok(fileResult);
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
