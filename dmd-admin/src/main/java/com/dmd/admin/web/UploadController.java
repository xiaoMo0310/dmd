package com.dmd.admin.web;

import com.dmd.FileUploadUtil;
import com.dmd.base.result.FileResult;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
