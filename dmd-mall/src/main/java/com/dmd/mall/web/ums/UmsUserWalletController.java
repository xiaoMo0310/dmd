package com.dmd.mall.web.ums;


import com.alibaba.fastjson.JSONObject;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.UmsUserWallet;
import com.dmd.mall.service.UmsUserWalletService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户账户表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@RestController
@RequestMapping("/umsUserWallet")
@Api(value = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsUserWalletController extends BaseController {

    @Autowired
    private UmsUserWalletService umsUserWalletService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 检验用户账户支付密码
     * @param object
     *
     * @return the wrapper
     */
    @PostMapping("/accountPassword/check")
    @ApiOperation(httpMethod = "POST", value = "检验用户账户支付密码")
    @ApiImplicitParam(name ="password", value = "账户密码", dataType = "JSONObject")
    public Wrapper checkAccountPassword(@RequestBody JSONObject object) {
        logger.info("checkAccountPassword - 检验用户账户支付密码. object={}", object);
        UmsUserWallet userWallet = new UmsUserWallet();
        userWallet.setUserId(getLoginAuthDto().getUserId());
        userWallet = umsUserWalletService.selectOne(userWallet);
        String oldPassword = (String) object.get("password");
        return WrapMapper.ok(passwordEncoder.matches(oldPassword, userWallet.getWalletPassword()));
    }

    /**
     * 修改用户账户支付密码
     * @param object
     *
     * @return the wrapper
     */
    @PostMapping("/accountPassword/edit")
    @ApiOperation(httpMethod = "POST", value = "修改用户账户支付密码")
    @ApiImplicitParam(name ="newPwd, confirmPwd", value = "账户新密码与确定密码", dataType = "JSONObject")
    public Wrapper editAccountPassword(@RequestBody JSONObject object) {
        logger.info("checkAccountPassword - 修改用户账户支付密码. object={}", object);
        String confirmPwd = (String) object.get("confirmPwd");
        String newPassword = (String) object.get("newPwd");
        int result = umsUserWalletService.editAccountPassword(getLoginAuthDto(), confirmPwd, newPassword);
        return handleResult(result);
    }

}

