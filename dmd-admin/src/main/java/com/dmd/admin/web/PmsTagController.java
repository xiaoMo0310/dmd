package com.dmd.admin.web;

import com.dmd.admin.model.domain.PmsTag;
import com.dmd.admin.service.PmsTagService;
import com.dmd.core.support.BaseController;
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

    @PostMapping("/tag/saveOrUpdate")
    @ApiOperation(httpMethod = "POST", value = "添加或者修改标签")
    @ApiImplicitParam(name ="pmsTag", value = "添加或者修改的内容", dataType = "PmsTag", paramType = "body")
    public Wrapper delete(@RequestBody PmsTag pmsTag) {
        int count = pmsTagService.saveOrUpdateTag( getLoginAuthDto(), pmsTag );
        return handleResult( count );
    }

    @GetMapping("/tag/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询标签")
    @ApiImplicitParam(name ="id", value = "标签id", dataType = "long", paramType = "path")
    public Wrapper delete(@PathVariable Long id) {
        PmsTag pmsTag = pmsTagService.findTagById( id );
        return WrapMapper.ok(pmsTag);
    }

    @PostMapping("/tagList/findByPage")
    @ApiOperation(httpMethod = "POST", value = "分页查询标签数据")
    @ApiImplicitParam(name ="pmsTag", value = "条件数据", dataType = "PmsTag", paramType = "body")
    public Wrapper findTabList(@RequestBody PmsTag pmsTag) {
        PageInfo<PmsTag> pmsTagPageInfo = pmsTagService.findTagListByPage( pmsTag );
        return WrapMapper.ok(pmsTagPageInfo);
    }

    @PostMapping("/tag/delete")
    @ApiOperation(httpMethod = "POST", value = "删除标签")
    @ApiImplicitParam(name ="ids", value = "标签id", dataType = "list", paramType = "query")
    public Wrapper delete(@RequestParam List<Long> ids) {
        int count = pmsTagService.deleteTag( ids );
        return handleResult( count );
    }

    @PostMapping("/tag/showStatus/update")
    @ApiOperation(httpMethod = "POST", value = "修改显示状态")
    @ApiImplicitParams( {@ApiImplicitParam(name ="ids", value = "标签id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name ="showStatus", value = "显示状态", dataType = "int", paramType = "query")
    } )
    public Wrapper updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = pmsTagService.updateShowStatus(getLoginAuthDto(), ids, showStatus);
        return handleResult( count );
    }

}

