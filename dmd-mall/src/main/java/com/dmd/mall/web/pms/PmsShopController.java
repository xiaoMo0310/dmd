package com.dmd.mall.web.pms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.PmsComment;
import com.dmd.mall.service.PmsShopService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shop")
@Api(value = "商场", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,description = "商场",tags = "PmsShopController")
public class PmsShopController {
    @Autowired
    private PmsShopService pmsShopService;

    @ApiOperation("获取商场首页数据")
    @RequestMapping(value = "/getShop", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String,Object>> getShopIndex(@RequestBody Map<String,Object> map) {
        Map<String,Object> productList = pmsShopService.getShopIndex(map);
        return CommonResult.success(productList);
    }

    @ApiOperation("商品详情和商品评论")
    @RequestMapping(value = "/shopProductDetails", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String,Object>> shopProductDetails(@RequestBody Map<String,Object> mapParam) {
        Map<String,Object> map = pmsShopService.shopProductDetails(mapParam);
        return CommonResult.success(map);
    }
    @ApiOperation("全部商品评论")
    @RequestMapping(value = "/shopProductComment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PageInfo<PmsComment>> shopProductComment(@RequestBody Map<String,Object> mapParam) {
        int id=(int)mapParam.get("id");
        int page=(int)mapParam.get("page");
        int pageSize=(int)mapParam.get("pageSize");
        PageInfo<PmsComment> map = pmsShopService.shopProductComment(id,page,pageSize);
        return CommonResult.success(map);
    }
}
