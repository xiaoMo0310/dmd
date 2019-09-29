package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.PmsDict;
import com.dmd.mall.model.vo.PmsDictVo;
import com.dmd.mall.service.PmsDictService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-27
 */
@RestController
@RequestMapping("/pmsDict")
@Api(value = "字典数据", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsDictController extends BaseController {

    @Autowired
    private PmsDictService pmsDictService;

    @GetMapping(value = "/value/findByKey")
    @ApiOperation(httpMethod = "POST", value = "根据Key获得Value")
    public Wrapper queryValueByKey(String parentKey, String key) {
        PmsDictVo mdcDictVo = pmsDictService.queryValueByKey(parentKey, key);
        return WrapMapper.ok(mdcDictVo);
    }

    @PostMapping(value = "/dict/save")
    @ApiOperation(httpMethod = "POST", value = "编辑字典如果没有创建如果有修改")
    public Wrapper saveDict(@ApiParam(name = "mdcDict", value = "编辑字典") @RequestBody PmsDict pmsDict) {
        int result = pmsDictService.saveMdcDict(pmsDict, getLoginAuthDto());
        return handleResult(result);
    }

}

