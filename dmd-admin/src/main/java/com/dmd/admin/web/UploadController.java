package com.dmd.admin.web;

import com.dmd.FileUploadUtil;
import com.dmd.base.result.FileResult;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 17:57
 * @Description 文件上传
 * */
@Controller
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @PostMapping("/file")
    @ApiOperation(httpMethod = "POST",value = "上传片")
    public Wrapper<FileResult> uploadImage(@ApiParam(name = "multipartFile",value = "上传多图片") MultipartFile multipartFile) throws IOException {
        FileResult fileResult = fileUploadUtil.saveFile(multipartFile, "adv", true);
        return WrapMapper.ok(fileResult);
    }
}
