package com.dmd.mall.web.oms;


import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.model.domain.OmsShipping;
import com.dmd.mall.model.dto.OmsShippingDto;
import com.dmd.mall.service.OmsShippingService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收货人信息表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-20
 */
@RestController
@RequestMapping("/omsShipping")
@Api(value = "收货地址管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsShippingController extends BaseController {

    @Autowired
    private OmsShippingService omsShippingService;

    /**
     * 增加收货人地址.
     *
     * @param shipping the shipping
     *
     * @return the wrapper
     */
    @PostMapping("/addShipping")
    @ApiOperation(httpMethod = "POST", value = "增加收货人地址")
    @ApiImplicitParam(name ="shipping", value = "收货人地址信息", dataType = "OmsShippingDto", paramType = "body")
    public Wrapper addShipping(@RequestBody OmsShippingDto shipping) {
        logger.info("addShipping - 增加收货人地址. shipping={}", shipping);
        int result = omsShippingService.saveShipping(getLoginAuthDto(), shipping);
        return handleResult(result);

    }

    /**
     * 删除收货人地址.
     *
     * @param shippingId the shipping id
     *
     * @return the wrapper
     */
    @GetMapping("/deleteShipping")
    @ApiOperation(httpMethod = "GET", value = "删除收货人地址")
    @ApiParam(name = "shippingId", value = "收货人地址id")
    @ApiImplicitParam(name ="shippingId", value = "收货人地址id", dataType = "long", paramType = "query")
    public Wrapper deleteShipping(@RequestParam("shippingId") Long shippingId) {
        int result = omsShippingService.deleteShipping(getLoginAuthDto(), shippingId);
        return handleResult(result);
    }

    @PostMapping("/updateShipping")
    @ApiOperation(httpMethod = "POST", value = "编辑收货人地址")
    @ApiImplicitParam(name ="omsShippingDto", value = "收货人地址信息", dataType = "OmsShippingDto")
    public Wrapper updateShipping(@RequestBody OmsShippingDto omsShippingDto) {
        logger.info("updateShipping - 编辑收货人地址. shipping={}", omsShippingDto);
        int result = omsShippingService.saveShipping(getLoginAuthDto(), omsShippingDto);
        return handleResult(result);
    }

    /**
     * 设置默认收货地址.
     *
     * @param shippingId the shipping id
     *
     * @return the default address
     */
    @GetMapping("/setDefaultAddress")
    @ApiOperation(httpMethod = "GET", value = "设置默认收货地址")
    @ApiImplicitParam(name ="shippingId", value = "收货人地址id", dataType = "long", paramType = "query")
    public Wrapper setDefaultAddress(@RequestParam("shippingId") Long shippingId) {
        logger.info("updateShipping - 设置默认地址. addressId={}", shippingId);
        int result = omsShippingService.setDefaultAddress(getLoginAuthDto(), shippingId);
        return handleResult(result);
    }

    @PostMapping("/selectShippingById")
    @ApiOperation(httpMethod = "POST", value = "查询用户默认的收货地址")
    public Wrapper<OmsShipping> selectShippingById() {
        OmsShipping omsShipping = omsShippingService.selectByShippingIdUserId(getLoginAuthDto());
        if(omsShipping == null){
            throw new OmsBizException("暂无收货地址信息");
        }
        return WrapMapper.ok(omsShipping);
    }

    /**
     * 分页查询当前用户收货人地址列表.
     *
     * @param baseQuery the page
     *
     * @return the wrapper
     */
    @PostMapping("queryUserShippingListWithPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询当前用户收货人地址列表")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery")
    public Wrapper<PageInfo> queryUserShippingListWithPage(@RequestBody BaseQuery baseQuery) {
        PageInfo pageInfo = omsShippingService.queryListWithPageByUserId(getLoginAuthDto(), baseQuery.getPageNum(), baseQuery.getPageSize());
        return WrapMapper.ok(pageInfo);
    }

}

