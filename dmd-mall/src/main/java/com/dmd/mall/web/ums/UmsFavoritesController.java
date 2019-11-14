package com.dmd.mall.web.ums;


import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.result.CommonResult;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.mall.model.dto.UmsFavoritesDto;
import com.dmd.mall.model.vo.UmsFavoritesVo;
import com.dmd.mall.service.UmsFavoritesService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * UmsFavoritesController 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/umsFavorites")
@Api(tags = "UmsFavoritesController", description = "用户关注", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsFavoritesController extends BaseController {

    @Autowired
    private UmsFavoritesService umsFavoritesService;

    @PostMapping("/attention/save")
    @ApiOperation(httpMethod = "POST", value = "编辑用户关注的信息")
    @ApiImplicitParam(name ="umsFavoritesDto", value = "用户关注的信息,修改需要提供id", dataType = "UmsFavoritesDto")
    public Wrapper saveAttentionMessage(@RequestBody UmsFavoritesDto umsFavoritesDto) {
        logger.info("saveAttentionMessage - 添加用户关注的信息. umsFavorites={}", umsFavoritesDto);
        int result = umsFavoritesService.saveAttentionMessage(getLoginAuthDto(), umsFavoritesDto);
        return handleResult(result);
    }

    @PostMapping("/attentionStatus/update")
    @ApiOperation(httpMethod = "POST", value = "修改用户的关注状态")
    @ApiImplicitParam(name ="umsFavoritesDto", value = "用户关注的信息,修改需要提供id", dataType = "UmsFavoritesDto")
    public Wrapper upda(@RequestBody UmsFavoritesDto umsFavoritesDto) {
        logger.info("saveAttentionMessage - 修改用户的关注状态. umsFavorites={}", umsFavoritesDto);
        int result = umsFavoritesService.updateAttentionStatus(getLoginAuthDto(), umsFavoritesDto);
        return handleResult(result);
    }

    /**
     * 判断用户时候关注用户 商品 商铺
     * @param targetId
     * @return the wrapper
     */
    @GetMapping("/attention/check")
    @ApiOperation(httpMethod = "GET", value = "判断用户时候关注用户 商品 商铺")
    @ApiImplicitParams({@ApiImplicitParam(name ="targetId", value = "目标对象id", dataType = "Long", paramType = "path"),
                        @ApiImplicitParam(name ="favoriteType", value = "关注类型(1:普通用户 2:教练用户 3:普通商品 4:课程或潜水商品 5:商铺)", dataType = "int", paramType = "path")})
    public Wrapper<Boolean> checkAttention(@RequestParam("targetId") Long targetId, @RequestParam("favoriteType") Integer favoriteType) {
        logger.info("checkAttention - 判断用户时候关注用户 商品 商铺. userId={}, targetId={}", targetId);
        Boolean flag = umsFavoritesService.checkAttention(getLoginAuthDto(), targetId, favoriteType);
        return WrapMapper.ok(flag);
    }

    /**
     * 查询我的关注
     * @param baseQuery
     * @return
     */
    @PostMapping("/favoritesList/find")
    @ApiOperation(httpMethod = "POST", value = "查询当前登录人关注的信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery")
    public Wrapper<JSONObject> findFavoritesList(@RequestBody BaseQuery baseQuery) {
        JSONObject jsonObject = umsFavoritesService.queryAttention(getLoginAuthDto().getUserId(), baseQuery);
        return WrapMapper.ok(jsonObject);
    }

}

