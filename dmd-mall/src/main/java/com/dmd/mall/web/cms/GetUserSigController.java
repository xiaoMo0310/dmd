package com.dmd.mall.web.cms;

import com.dmd.TLSSigAPIv2;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChenYanbing
 * @title: GetUserSigController
 * @projectName dmd-masters
 * @description:
 */
@Controller
@Api(tags = "GetUserSigController", description = "腾讯云通讯生成UserSig")
@RequestMapping("/get")
public class GetUserSigController {

    @ApiOperation("即时通讯UserSig")
    @RequestMapping(value = "/getUserSig",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> getUserSig(){
        //sdkappid
        //key：秘钥
        //identifier:帐号管理员ID
        TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(1400301957,"d3066baeb58329e7a92db0c65b33fa074f33b2a53c0446b795b194ed79e78e41");
        String s = tlsSigAPIv2.genSig("香港誼行悅航互聯網",1400301957);
        return  CommonResult.success(s);
    }
}
