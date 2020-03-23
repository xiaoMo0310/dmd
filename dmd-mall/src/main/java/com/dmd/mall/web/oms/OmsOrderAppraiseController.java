package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.OrderAppraiseDto;
import com.dmd.mall.service.OmsOrderAppraiseService;
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
 *  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/appraise")
@Api(tags = "OmsOrderAppraiseController", description = "订单评价中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsOrderAppraiseController extends BaseController {

    @Autowired
    private OmsOrderAppraiseService omsOrderAppraiseService;

    @PostMapping("/appraise/order")
    @ApiOperation(httpMethod = "POST", value = "评价商品订单")
    @ApiImplicitParam(name ="orderAppraiseDto", value = "订单编号", dataType = "OrderAppraiseDto", paramType = "body")
    public Wrapper appraiseProductOrder(@RequestBody OrderAppraiseDto orderAppraiseDto) {
        int result = omsOrderAppraiseService.insertAppraiseMessage(getLoginAuthDto(), orderAppraiseDto);
        return handleResult(result);
    }

    @GetMapping("/appraiseMessage/findList")
    @ApiOperation(httpMethod = "GET", value = "查询商品的评价信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="productId", value = "商品id", dataType = "Long", paramType = "query"),
                        @ApiImplicitParam(name ="level", value = "评价等级(-1差评 0中评 1好评)", dataType = "String", paramType = "query"),
                        @ApiImplicitParam(name ="pageNum", value = "页数", dataType = "int", paramType = "query"),
                        @ApiImplicitParam(name ="pageSize", value = "每页显示条数", dataType = "int", paramType = "query")})
    public Wrapper findAppraiseMessage(@RequestParam("productId") String productId,
                                       @RequestParam("productId") String level,
                                          @RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = omsOrderAppraiseService.findAppraiseMessage(productId, level, pageNum, pageSize);
        return handleResult(pageInfo);
    }

    @GetMapping("/appraise/order/delete")
    @ApiOperation(httpMethod = "GET", value = "删除订单评价")
    @ApiImplicitParam(name ="id", value = "评价id", dataType = "long", paramType = "query")
    public Wrapper deleteProductAppraise(@RequestParam Long id) {
        int result = omsOrderAppraiseService.deleteByKey( id );
        return handleResult(result);
    }


}

