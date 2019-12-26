package com.dmd.mall.web.cms;

import com.dmd.TLSSigAPIv2;
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
    public String getUserSig(){
        //sdkappid
        //key：秘钥
        //identifier:帐号管理员ID
        TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(1400282640,"9bbe80d39f42495d19889c66fb8f178dd4cae0bff1765ea5ac2df2b9868bd56f");
        String s = tlsSigAPIv2.genSig("administrator",1400282640);
        return  s;
    }
}
