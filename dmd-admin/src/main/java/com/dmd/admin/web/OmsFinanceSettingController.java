package com.dmd.admin.web;

import com.dmd.admin.model.domain.OmsFinanceSetting;
import com.dmd.admin.service.OmsFinanceSettingService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 财务设置表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/omsFinanceSetting")
@Api(tags = "OmsFinanceSettingController", description = "财务规则配置中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsFinanceSettingController extends BaseController {

    @Autowired
    private OmsFinanceSettingService financeSettingService;

    @GetMapping("/{id}")
    @ApiOperation(httpMethod = "GET", value = "获取指定财务规则设置")
    @ApiImplicitParam(name ="id", value = "规则id", dataType = "long", paramType = "path")
    public Wrapper getItem(@PathVariable Long id) {
        OmsFinanceSetting omsFinanceSetting = financeSettingService.selectByKey(id);
        return WrapMapper.ok(omsFinanceSetting);
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改财务规则")
    @ApiImplicitParam(name ="omsFinanceSetting", value = "要修改的数据", dataType = "OmsFinanceSetting", paramType = "body")
    public Wrapper update(@RequestBody OmsFinanceSetting omsFinanceSetting) {
        omsFinanceSetting.setUpdateInfo( getLoginAuthDto() );
        int count = financeSettingService.update(omsFinanceSetting);
        return handleResult( count );
    }

}

