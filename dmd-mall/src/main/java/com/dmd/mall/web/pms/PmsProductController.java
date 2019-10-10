package com.dmd.mall.web.pms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.service.PmsProductService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-09
 */
@RestController
@RequestMapping("/pms")
@Api(value = "全部商品信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsProductController extends BaseController {

    @Autowired
    private PmsProductService pmsProductService;

    @PostMapping("/shipSleepsProduct/findPage")
    @ApiOperation(httpMethod = "GET", value = "分页查询船宿商品的列表信息")
    @ApiImplicitParam(name ="sortDto", value = "排序分页数据", dataType = "SortDto")
    public Wrapper findShipSleepsProduct(@RequestBody SortDto sortDto) {
        PageInfo list = pmsProductService.findShipSleepsProduct(sortDto);
        return WrapMapper.ok(list);
    }
}

