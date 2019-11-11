package com.dmd.mall.web.pms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.PmsPlayAddressVo;
import com.dmd.mall.service.PmsPlayAddressService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 潜水学习地址表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/pmsPlayAddress")
@Api(tags = "PmsPlayAddressController", description = "潜水学习地址中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsPlayAddressController extends BaseController {

    @Autowired
    private PmsPlayAddressService pmsPlayAddressService;

    @GetMapping("/playAddress/findAll")
    @ApiOperation(httpMethod = "GET", value = "查询所有的游玩地址")
    public Wrapper findAllPlayAddress() {
        List<PmsPlayAddressVo> pmsPlayAddressVos = pmsPlayAddressService.findAllPlayAddress();
        return WrapMapper.ok(pmsPlayAddressVos);
    }
}

