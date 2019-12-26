package com.dmd.admin.web;


import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.PmsPlayAddress;
import com.dmd.admin.model.dto.PmsPlayAddressDto;
import com.dmd.admin.service.PmsPlayAddressService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 潜水学习地址表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsPlayAddressController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsPlayAddressController extends BaseController {

    @Autowired
    private PmsPlayAddressService pmsPlayAddressService;

    @PostMapping("/playAddress/findByPage")
    @ApiOperation(httpMethod = "POST", value = "查询潜水地址集合")
    @ApiImplicitParam(name ="baseQuery", value = "搜索分页数据", paramType = "body", dataType = "BaseQuery")
    public Wrapper findCertificateList(@RequestBody BaseQuery baseQuery) {
        PageInfo<PmsPlayAddress> pageInfo = pmsPlayAddressService.findPlayAddressList(baseQuery);
        return WrapMapper.ok(pageInfo);
    }

    @OperationLog(content = "修改默认地址")
    @GetMapping("/isDefault/update/{id}")
    @ApiOperation(httpMethod = "GET", value = "修改默认地址")
    @ApiImplicitParam(name ="id", value = "id", paramType = "path", dataType = "long")
    public Wrapper updateIsDefautlById(@PathVariable Long id) {
        int result = pmsPlayAddressService.updateIsDefaultById(getLoginAuthDto(), id);
        return handleResult(result);
    }

    @OperationLog(content = "添加或修改潜水地址信息")
    @PostMapping("/playAddress/saveOrUpdate")
    @ApiOperation(httpMethod = "POST", value = "添加或修改潜水地址信息")
    @ApiImplicitParam(name ="pmsPlayAddressDto", value = "潜水地址信息", paramType = "body", dataType = "PmsPlayAddressDto")
    public Wrapper updateCertificateStatus(@RequestBody PmsPlayAddressDto pmsPlayAddressDto) {
        int result = pmsPlayAddressService.saveOrUpdate(getLoginAuthDto(), pmsPlayAddressDto);
        return handleResult(result);
    }

    @GetMapping("/playAddress/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询潜水地址信息")
    @ApiImplicitParam(name ="id", value = "主键id", dataType = "long", paramType = "path")
    public Wrapper findPlayAddressById(@PathVariable Long id) {
        PmsPlayAddress pmsPlayAddress = pmsPlayAddressService.findPlayAddressById(id);
        return WrapMapper.ok(pmsPlayAddress);
    }

    @OperationLog(content = "删除潜水地址信息")
    @ApiOperation("删除潜水地址信息")
    @RequestMapping(value = "/playAddress/delete", method = RequestMethod.POST)
    public Wrapper deletePlayAddress(@RequestParam("ids") List<Long> ids) {
        int result = pmsPlayAddressService.deleteByIds(ids);
        return handleResult(result);
    }
}

