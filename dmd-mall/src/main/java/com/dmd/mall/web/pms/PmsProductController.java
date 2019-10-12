package com.dmd.mall.web.pms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsProductVo;
import com.dmd.mall.service.PmsProductService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "商品信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsProductController extends BaseController {

    @Autowired
    private PmsProductService pmsProductService;

    @PostMapping("/shipSleepsProduct/findPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询船宿商品的列表信息")
    @ApiImplicitParam(name ="sortDto", value = "排序分页数据", dataType = "SortDto")
    public Wrapper findShipSleepsProduct(@RequestBody SortDto sortDto) {
        PageInfo list = pmsProductService.findShipSleepsProduct(sortDto);
        return WrapMapper.ok(list);
    }

    @GetMapping("/shipSleepsProduct/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据商品的id查询商品的详细信息")
    @ApiImplicitParam(name ="id", value = "商品id", dataType = "Long", paramType = "path")
    public Wrapper<PmsProductVo> findShipSleepsMessage(@PathVariable Long id) {
        PmsProductVo pmsProductVo = pmsProductService.findShipSleepsMessage(id);
        return WrapMapper.ok(pmsProductVo);
    }
}

