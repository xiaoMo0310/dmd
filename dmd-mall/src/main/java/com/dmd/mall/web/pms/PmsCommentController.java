package com.dmd.mall.web.pms;


import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsComment;
import com.dmd.mall.service.PmsCommentService;
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
 * 商品评价表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@RestController
@RequestMapping("/pmsComment")
@Api(value = "商品评价", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCommentController extends BaseController {

    @Autowired
    private PmsCommentService pmsCommentService;

    @PostMapping("/comment/findPage/{productId}")
    @ApiOperation(httpMethod = "POST", value = "根据商品id分页查询评论信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery"),
                        @ApiImplicitParam(name ="productId", value = "商品id", dataType = "Long", paramType = "path")})
    public Wrapper findShipSleepsProduct(@RequestBody BaseQuery baseQuery, @PathVariable Long productId) {
        PageInfo<PmsComment> list = pmsCommentService.findCommentMessge(baseQuery, productId);
        return WrapMapper.ok(list);
    }

}

