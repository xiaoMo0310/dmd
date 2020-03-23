package com.dmd.mall.web.pms;

import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsTag;
import com.dmd.mall.service.PmsTagService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-16
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsTagController", description = "标签中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsTagController extends BaseController {

    @Autowired
    private PmsTagService pmsTagService;

    @GetMapping("/tag/findByType")
    @ApiOperation(httpMethod = "GET", value = "根据类型查询所有的标签")
    @ApiImplicitParam(name ="tagType", value = "标签类型(1:产品)", dataType = "long", paramType = "query")
    public Wrapper delete(@RequestParam Long tagType) {
        List<PmsTag> pmsTags = pmsTagService.findTagByType( tagType );
        return WrapMapper.ok(pmsTags);
    }

}

