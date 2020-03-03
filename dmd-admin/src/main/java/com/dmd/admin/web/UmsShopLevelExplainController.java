package com.dmd.admin.web;

import com.dmd.admin.model.domain.UmsShopLevelExplain;
import com.dmd.admin.service.UmsShopLevelExplainService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
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
 * 教练店铺店铺表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsShopLevelExplainController", description = "店铺等级说明", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsShopLevelExplainController extends BaseController {

    @Autowired
    private UmsShopLevelExplainService umsShopLevelExplainService;

    @PostMapping("/shop/levelExplain/saveOrUpdate")
    @ApiOperation(httpMethod = "POST", value = "添加或修改店铺等级说明")
    @ApiImplicitParam(name ="shopLevelExplain", value = "等级说明数据", paramType = "UmsShopLevelExplain", dataType = "body")
    public Wrapper saveMessage(@RequestBody UmsShopLevelExplain shopLevelExplain) {
        int result = umsShopLevelExplainService.saveOrUpdateShopLevelExplain(getLoginAuthDto(), shopLevelExplain);
        return handleResult( result );
    }

    @GetMapping("/shop/levelExplain/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询店铺等级说明")
    @ApiImplicitParam(name ="id", value = "店铺等级说明id", paramType = "long", dataType = "path")
    public Wrapper findShopNoticeList(@PathVariable Long id ) {
        UmsShopLevelExplain shopNoticePageInfo = umsShopLevelExplainService.selectByKey(id);
        return WrapMapper.ok(shopNoticePageInfo);
    }

    @PostMapping("/shop/levelExplain/find")
    @ApiOperation(httpMethod = "POST", value = "查询所有店铺等级说明")
    public Wrapper findShopNoticeList() {
        BaseQuery baseQuery = new BaseQuery();
        PageHelper.startPage( baseQuery.getPageNum(), baseQuery.getPageSize(), baseQuery.getOrderBy());
        List<UmsShopLevelExplain> shopLevelExplains = umsShopLevelExplainService.selectAll();
        return WrapMapper.ok(new PageInfo<UmsShopLevelExplain>( shopLevelExplains ));
    }

}

