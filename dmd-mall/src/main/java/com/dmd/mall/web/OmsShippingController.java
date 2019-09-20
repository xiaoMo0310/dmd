package com.dmd.mall.web;


import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.OmsShipping;
import com.dmd.mall.service.OmsShippingService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiImplicitParams(@ApiImplicitParam(name = "shipping", value = "收货人地址信息"))
    public Wrapper addShipping(@RequestBody OmsShipping shipping) {

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
    @PostMapping("/deleteShipping/{shippingId}")
    @ApiOperation(httpMethod = "POST", value = "删除收货人地址")
    @ApiImplicitParam(name = "shippingId", value = "收货人地址id")
    public Wrapper deleteShipping(@PathVariable Integer shippingId) {
        Long userId = getLoginAuthDto().getUserId();
        logger.info("deleteShipping - 删除收货人地址. userId={}, shippingId={}", userId, shippingId);
        int result = omsShippingService.deleteShipping(userId, shippingId);
        return handleResult(result);
    }

    /**
     * 编辑收货人地址.
     *
     * @param shipping the shipping
     *
     * @return the wrapper
     */
    @PostMapping("/updateShipping")
    @ApiOperation(httpMethod = "POST", value = "编辑收货人地址")
    @ApiImplicitParam(name = "shipping", value = "收货人地址信息")
    public Wrapper updateShipping(@RequestBody OmsShipping shipping) {
        logger.info("updateShipping - 编辑收货人地址. shipping={}", shipping);
        int result = omsShippingService.saveShipping(getLoginAuthDto(), shipping);
        return handleResult(result);
    }

    /**
     * 设置默认收货地址.
     *
     * @param shippingId the shipping id
     *
     * @return the default address
     */
    @PostMapping("/setDefaultAddress/{addressId}")
    @ApiOperation(httpMethod = "POST", value = "设置默认收货地址")
    @ApiImplicitParam(name = "shippingId", value = "收货人地址id")
    public Wrapper setDefaultAddress(@PathVariable Long shippingId) {
        logger.info("updateShipping - 设置默认地址. addressId={}", shippingId);
        int result = omsShippingService.setDefaultAddress(getLoginAuthDto(), shippingId);
        return handleResult(result);
    }

    /**
     * 根据Id查询收货人地址.
     *
     * @param shippingId the shipping id
     *
     * @return the wrapper
     */
    @PostMapping("/selectShippingById/{shippingId}")
    @ApiOperation(httpMethod = "POST", value = "根据Id查询收货人地址")
    @ApiImplicitParam(name = "shippingId", value = "收货人地址id")
    public Wrapper<OmsShipping> selectShippingById(@PathVariable Long shippingId) {
        Long userId = getLoginAuthDto().getUserId();
        logger.info("selectShippingById - 根据Id查询收货人地址. userId={}, shippingId={}", userId, shippingId);
        OmsShipping OmsShipping = omsShippingService.selectByShippingIdUserId(userId, shippingId);
        return WrapMapper.ok(OmsShipping);
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
    @ApiImplicitParam(name = "baseQuery", value = "分页数据")
    public Wrapper<PageInfo> queryUserShippingListWithPage(@RequestBody BaseQuery baseQuery) {
        Long userId = getLoginAuthDto().getUserId();
        logger.info("queryUserShippingListWithPage - 分页查询当前用户收货人地址列表.userId={} shipping={}", userId, baseQuery);
        PageInfo pageInfo = omsShippingService.queryListWithPageByUserId(userId, baseQuery.getPageNum(), baseQuery.getPageSize());
        return WrapMapper.ok(pageInfo);
    }

}

