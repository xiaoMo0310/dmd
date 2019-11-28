package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenYanbing
 * @title: MyfileController
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/10/813:06
 */

@Controller
@Api(tags = "MyfileController", description = "视频与图片上传",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/uploadFile")
public class MyfileController {

    @ApiOperation("视频上传")
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult uploadFile(@RequestParam("fileName") MultipartFile file) {
        Map<String,Object> map=new HashMap<>();
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

        map.put("path",relativePath);
        return CommonResult.success(map);
    }

    /*@RequestMapping("/filesUpload")
    //查
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files,
                              HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        return "index";//跳转的页面
    }*/
/*
    @ApiOperation("图片上传")
    @RequestMapping(value="/saveFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public CommonResult filesUpload(@RequestParam(value = "myfiles" ) MultipartFile[] files,
                              HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //测试
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        // 数组转String字符串
        String newStr = StringUtils.join(list, ",");
        map.put("paths",newStr);
        return CommonResult.success(map);
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename();
                *//*String filePath = "/project/tomcat/webapps/fileUpload/picture" + fileName;
                String relativePath="fileUpload/picture" +fileName;*//*
                String relativePath="http://192.168.0.246/dmd/" +fileName;
                String filePath = "D:/home/dmd/"+ fileName;

                list.add(relativePath);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }*/
    @ApiOperation("图片上传")
    @PostMapping(value="/saveFile")
    @ResponseBody
    public CommonResult filesUpload(@RequestParam("files") MultipartFile[] files,
                              HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //测试
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        // 数组转String字符串
        String newStr = StringUtils.join(list, ",");
        System.out.println(newStr);
        return CommonResult.success(newStr);
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
                String fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename();
                //*String filePath = "/project/tomcat/webapps/fileUpload/picture" + fileName;
                String relativePath="http://192.168.0.246/dmd/" +fileName;
                String filePath = "D:/home/dmd/"+ fileName;
                list.add(filePath);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}