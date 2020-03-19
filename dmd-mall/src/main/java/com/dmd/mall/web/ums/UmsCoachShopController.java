package com.dmd.mall.web.ums;

import com.dmd.PublicUtil;
import com.dmd.core.support.BaseController;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.model.domain.UmsCoachShop;
import com.dmd.mall.model.dto.UmsCoachShopDto;
import com.dmd.mall.model.vo.UmsCoachShopVo;
import com.dmd.mall.service.UmsCoachShopService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 教练店铺店铺表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsCoachShopController", description = "教练店铺中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachShopController extends BaseController {

    @Autowired
    private UmsCoachShopService umsCoachShopService;

    @PostMapping("/shop/message/saveOrEdit")
    @ApiOperation(httpMethod = "GET", value = "保存店铺信息")
    @ApiImplicitParam(name ="umsCoachShopDto", value = "店铺信息", dataType = "UmsCoachShopDto", paramType = "body")
    public Wrapper findLoginUserMessage(@RequestBody UmsCoachShopDto umsCoachShopDto, HttpServletRequest request) {
        int result = umsCoachShopService.saveOrEditShopMessage(getLoginAuthDto(), umsCoachShopDto, request);
        return handleResult( result );
    }

    @PostMapping("/shop/message/findByCoachId")
    @ApiOperation(httpMethod = "GET", value = "查询店铺信息及粉丝数量")
    public Wrapper findShopMessageByCoachId() {
        UmsCoachShopVo umsCoachShop = umsCoachShopService.findShopMessage( getLoginAuthDto() );
        if(PublicUtil.isEmpty( umsCoachShop )){
            throw new UmsBizException( "未查询到店铺信息" );
        }
        return WrapMapper.ok( umsCoachShop );
    }

    @PostMapping("/shop/message/findById")
    @ApiOperation(httpMethod = "GET", value = "根据店铺id查询店铺信息")
    @ApiImplicitParam(name ="shopId", value = "店铺id", dataType = "long", paramType = "query")
    public Wrapper findShopMessageByShopId(@RequestParam Long shopId) {
        UmsCoachShop umsCoachShop = umsCoachShopService.selectByKey( shopId );
        if(PublicUtil.isEmpty( umsCoachShop )){
            throw new UmsBizException( "未查询到店铺信息" );
        }
        return WrapMapper.ok( umsCoachShop );
    }

}

