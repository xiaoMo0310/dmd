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
 * @description: TODO
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
        /*Map<String,Object> map=new HashMap<>();
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            map.put("error","文件已经存在");
            return CommonResult.success(map);
        }


        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");

        //加个时间戳，尽量避免文件名称重复
        //String path = "/project/tomcat/webapps/afileUpload/video" +fileName;
        String relativePath="http://192.168.0.246/dmd/" +fileName;
        String path = "D:/home/dmd/"+ fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            map.put("error","文件已经存在");
            return CommonResult.success(map);
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件

        } catch (IOException e) {
            map.put("error","上传失败");
            return CommonResult.success(map);
        }
        map.put("path",relativePath);*/
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