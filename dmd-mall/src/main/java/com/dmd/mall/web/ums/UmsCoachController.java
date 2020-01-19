package com.dmd.mall.web.ums;


import com.dmd.FileUploadUtil;
import com.dmd.TaoBaoUtil;
import com.dmd.base.result.CommonResult;
import com.dmd.base.result.FileResult;
import com.dmd.core.annotation.ApiVersion;
import com.dmd.core.support.BaseController;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.model.dto.UmsCoachRegisterDto;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 教练表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/sso")
@Api(tags = "UmsCoachController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachController extends BaseController {

    @Autowired
    private UmsCoachService umsCoachService;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @ApiOperation(httpMethod = "POST", value ="教练注册")
    @PostMapping(value = "/coach/register")
    @ApiImplicitParams({@ApiImplicitParam(name ="coachRegisterDto", value = "注册信息", dataType = "UmsCoachRegisterDto", paramType = "body"),
            @ApiImplicitParam(name ="deviceId", value = "deviceId", dataType = "String", paramType = "header")})
    public Wrapper register(@RequestBody UmsCoachRegisterDto coachRegisterDto, HttpServletRequest request) {
        int result = umsCoachService.register(coachRegisterDto,request);
        return handleResult(result);
    }

    @ApiOperation(httpMethod = "POST", value ="教练端找回密码")
    @PostMapping(value = "/coach/findPassword")
    @ApiImplicitParam(name ="findPasswordDto", value = "找回密码信息", dataType = "FindPasswordDto", paramType = "body")
    public Wrapper findPassword(@RequestBody FindPasswordDto findPasswordDto, HttpServletRequest request) {
        return umsCoachService.findPassword(findPasswordDto.getTelephone(), findPasswordDto.getNewPassword(),findPasswordDto.getConfirmPassword(), findPasswordDto.getAuthCode(),request);
    }

    @ApiOperation("图片上传")
    @PostMapping(value="/saveFile")
    @ResponseBody
    public CommonResult filesUpload(@RequestParam("files") MultipartFile[] files) {
        List<FileResult> fileResults = fileUploadUtil.saveFiles(files, "coach", true);
        List<String> urlList = fileResults.stream().map(fileResult -> fileResult.getServerPath()).collect(Collectors.toList());
        String newStr = StringUtils.join(urlList, ",");
        return CommonResult.success(newStr);
    }

    @ApiOperation("获取ip")
    @PostMapping(value="/findIp")
    @ResponseBody
    public CommonResult getIp(HttpServletRequest request) {
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        String temp = "127.0.";
        String temp2 = "192.168.";
        String temp3 = "0:0:";
        String location = remoteAddr;
        if (location.startsWith(temp) || location.startsWith(temp2) || location.startsWith(temp3)) {
            location = "111.199.188.14";
        }
        logger.info(remoteAddr);
        logger.info(location);
        String remoteLocation = TaoBaoUtil.getCityByIpAddr(location);
        logger.info(remoteLocation);
        return CommonResult.success(remoteLocation);
    }
    @ApiVersion(1)
    @ApiOperation("获取ip")
    @PostMapping(value="/findIp")
    @ResponseBody
    public CommonResult getIpA(HttpServletRequest request) {
        final String remoteAddr = RequestUtil.getRemoteAddr(request);
        String temp = "127.0.";
        String temp2 = "192.168.";
        String temp3 = "0:0:";
        String location = remoteAddr;
        if (location.startsWith(temp) || location.startsWith(temp2) || location.startsWith(temp3)) {
            location = "111.199.188.14";
        }
        logger.info(remoteAddr);
        logger.info(location);
        String remoteLocation = TaoBaoUtil.getCityByIpAddr(location);
        logger.info(remoteLocation);
        return CommonResult.success(remoteLocation);
    }
}

