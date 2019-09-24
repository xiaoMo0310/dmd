package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.UmsFavorites;
import com.dmd.mall.service.UmsFavoritesService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加用户关注的信息
     * @param umsFavorites
     *
     * @return the wrapper
     */
    @PostMapping("/attention/save")
    @ApiOperation(httpMethod = "POST", value = "编辑用户关注的信息")
    @ApiImplicitParam(name ="umsFavorites", value = "用户关注的信息,修改需要提供id", dataType = "UmsFavorites")
    public Wrapper saveAttentionMessage(@RequestBody UmsFavorites umsFavorites) {
        logger.info("addAttentionMessage - 添加用户关注的信息. umsFavorites={}", umsFavorites);
        int result = umsFavoritesService.saveAttentionMessage(getLoginAuthDto(), umsFavorites);
        return handleResult(result);
    }

    /**
     * 判断用户时候关注用户 商品 商铺
     * @param targetId
     * @return the wrapper
     */
    @PostMapping("/attention/check/{targetId}/{favoriteType}")
    @ApiOperation(httpMethod = "POST", value = "判断用户时候关注用户 商品 商铺")
    @ApiImplicitParams({@ApiImplicitParam(name ="targetId", value = "目标对象id", dataType = "Long", paramType = "path"),
                        @ApiImplicitParam(name ="favoriteType", value = "关注类型(0:用户 1:商品 2:店铺)", dataType = "int", paramType = "path")})
    public Wrapper<Boolean> checkAttention(@PathVariable Long targetId, @PathVariable Integer favoriteType) {
        Long userId = getLoginAuthDto().getUserId();
        logger.info("checkAttention - 判断用户时候关注用户 商品 商铺. userId={}, targetId={}", userId, targetId);
        Boolean flag = umsFavoritesService.checkAttention(userId, targetId, favoriteType);
        return WrapMapper.ok(flag);
    }

}

