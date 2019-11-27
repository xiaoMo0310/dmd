package com.dmd.mall.web.pms;


import com.dmd.base.result.CommonResult;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.IntegralGiftsBean;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;
import com.dmd.mall.model.vo.IntegralProductVo;
import com.dmd.mall.service.DmdIntegralGiftService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/integralGifts")
@Api(tags = "DmdIntegralGiftController", description = "积分商品中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DmdIntegralGiftController extends BaseController {

    @Autowired
    private DmdIntegralGiftService dmdIntegralGiftService;

    /**
     * 查询全部积分好礼
     * @return
     */
    @ApiOperation("查询全部积分好礼")
    @RequestMapping(value = "/selectIntegralGifts",method = RequestMethod.GET)
    public CommonResult<List<IntegralGiftsBean>> queryIntegralGifts() {
        List<IntegralGiftsBean> integralGiftsList = dmdIntegralGiftService.queryIntegralGifts();
        return CommonResult.success(integralGiftsList);
    }

    /**
     * 分页查询全部积分好礼
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询全部积分好礼")
    @RequestMapping(value = "/selectIntegralGiftsPage",method = RequestMethod.GET)
    public CommonResult<PageInfo<IntegralGiftsBean>> queryIntegralGiftsPage(@RequestParam Integer pageNum,
                                                                            @RequestParam Integer pageSize) {
        List<IntegralGiftsBean> integralGiftsList = dmdIntegralGiftService.queryIntegralGiftsPage(pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(integralGiftsList));
    }

    /**
     * 根据礼品id查询好礼详情
     * @param 礼品 id
     * @return
     */
    @ApiOperation("根据礼品id查询好礼详情")
    @RequestMapping(value = "/selectIntegralGiftsById",method = RequestMethod.GET)
    public CommonResult<List<IntegralGiftsBean>> queryIntegralGiftsById(@RequestParam Long id) {
        List<IntegralGiftsBean> integralGiftsList = dmdIntegralGiftService.queryIntegralGiftsById(id);
        return CommonResult.success(integralGiftsList);
    }

    /**
     * 添加/修改积分好礼
     * @param integralGiftsBean
     * @return
     */
    @ApiOperation("添加/修改积分好礼")
    @RequestMapping(value = "/addIntegralGifts",method = RequestMethod.POST)
    public CommonResult addIntegralGifts(@RequestBody IntegralGiftsBean integralGiftsBean) {

        try {
            //有id修改,无id新增
            if(integralGiftsBean.getId()==null) {

                int count = dmdIntegralGiftService.addIntegralGifts(integralGiftsBean);
                if (count > 0) {
                    return CommonResult.success(count,"添加成功");
                }
                return CommonResult.failed("添加失败");
            }
            else{

                int count = dmdIntegralGiftService.updateIntegralGiftsById(integralGiftsBean);
                if (count > 0) {
                    return CommonResult.success(count,"修改成功");
                }
                return CommonResult.failed("修改失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return CommonResult.failed();
        }
    }



    /**
     * 回显积分好礼
     * @param 积分礼品 id
     * @return
     */
    @ApiOperation("回显积分好礼")
    @RequestMapping(value = "/findIntegralGiftsInfoById",method = RequestMethod.GET)
    public CommonResult<IntegralGiftsBean> findIntegralGiftsInfoById(@RequestParam Long id){
        IntegralGiftsBean integralGiftsBean = dmdIntegralGiftService.findIntegralGiftsInfoById(id);
        return CommonResult.success(integralGiftsBean);
    }

    /**
     * 删除积分好礼
     * @param id
     * @return
     */
    @ApiOperation("删除积分好礼")
    @RequestMapping(value = "/deleteIntegralGiftsById",method = RequestMethod.POST)
    public CommonResult deleteIntegralGiftsById(@RequestParam Long id){
        int count = dmdIntegralGiftService.deleteIntegralGiftsById(id);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 根据礼品id查询礼品规格
     * @param id
     * @return
     */
    @ApiOperation("兑换查询产品规格")
    @RequestMapping(value = "/selectIntegralGiftsSpeById",method = RequestMethod.GET)
    public CommonResult<List<IntegralGiftsSpeBean>> queryIntegralGiftsSpeById(@RequestParam Long id) {
        List<IntegralGiftsSpeBean> integralGiftsSpeList = dmdIntegralGiftService.queryIntegralGiftsSpeById(id);
        return CommonResult.success(integralGiftsSpeList);
    }

    /**
     * 查询各种规格对应库存
     * @param id
     * @param size
     * @param color
     * @return
     */
    @ApiOperation("查询各种规格对应库存")
    @RequestMapping(value = "/selectIntegralGiftsSpecStock",method = RequestMethod.GET)
    public CommonResult<Integer> selectIntegralGiftsSpecStock(@RequestParam Long id,String size,String color) {
        Integer specStock = dmdIntegralGiftService.selectIntegralGiftsSpecStock(id,size,color);
        return CommonResult.success(specStock);
    }


    @GetMapping("/integralProduct/settlement")
    @ApiOperation(httpMethod = "GET", value = "结算积分好礼商品")
    public Wrapper settlementIntegralProduct(@RequestParam("productSkuId") Long productSkuId,
                                             @RequestParam("productQuantity") Integer productQuantity) {
        IntegralProductVo integralProductVo = dmdIntegralGiftService.settlementIntegralProduct(getLoginAuthDto(),productSkuId, productQuantity);
        return WrapMapper.ok(integralProductVo);
    }

}

