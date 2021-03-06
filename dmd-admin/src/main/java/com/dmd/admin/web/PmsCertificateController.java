package com.dmd.admin.web;


import com.dmd.admin.model.domain.PmsCertificate;
import com.dmd.admin.model.dto.PmsCertificateDto;
import com.dmd.admin.service.PmsCertificateService;
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
import java.util.stream.Collectors;

/**
 * <p>
 * 证书数据表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/pms")
@Api(tags = "PmsCertificateController", description = "证书数据", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PmsCertificateController extends BaseController {

    @Autowired
    private PmsCertificateService pmsCertificateService;

    @PostMapping("/certificateList/findByPage")
    @ApiOperation(httpMethod = "POST", value = "查询全部的证书信息")
    @ApiImplicitParam(name ="PmsCertificateDto", value = "查询需要数据", paramType = "body", dataType = "BaseQuery")
    public Wrapper findCertificateList(@RequestBody PmsCertificateDto certificateDto) {
        PageInfo<PmsCertificate> pageInfo = pmsCertificateService.findCertificateList(certificateDto);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/certificate/saveOrUpdate")
    @ApiOperation(httpMethod = "POST", value = "添加或修改证书的信息")
    @ApiImplicitParam(name ="pmsCertificate", value = "证书数据", paramType = "body", dataType = "PmsCertificate")
    public Wrapper updateCertificateStatus(@RequestBody PmsCertificate pmsCertificate) {
        int result = pmsCertificateService.updateCertificateStatus(getLoginAuthDto(), pmsCertificate);
        return handleResult(result);
    }

    @GetMapping("/certificate/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询证书信息")
    @ApiImplicitParam(name ="id", value = "主键id", dataType = "long", paramType = "path")
    public Wrapper findCertificateById(@PathVariable Long id) {
        PmsCertificate pmsCertificate = pmsCertificateService.findCertificateById(id);
        return WrapMapper.ok(pmsCertificate);
    }

    @GetMapping("/certificateAndPage/findById/{id}/{pageSize}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询证书信息及页码")
    @ApiImplicitParams({@ApiImplicitParam(name ="id", value = "主键id", dataType = "long", paramType = "path"),
            @ApiImplicitParam(name ="pageSize", value = "每页显示条数", dataType = "int", paramType = "path")})
    public Wrapper findCertificateAndPageById(@PathVariable Long id, @PathVariable Integer pageSize) {
        PmsCertificate pmsCertificate = pmsCertificateService.findCertificateAndPageById(id, pageSize);
        return WrapMapper.ok(pmsCertificate);
    }

    @ApiOperation("删除证书信息")
    @RequestMapping(value = "/certificate/delete", method = RequestMethod.POST)
    public Wrapper deletePmsCertificate(@RequestParam("ids") List<Long> ids) {
        int count = pmsCertificateService.selectCertificateCount();
        List<PmsCertificate> pmsCertificates = ids.stream().map(id -> {
            PmsCertificate certificate = pmsCertificateService.selectByKey(id);
            if(count != Integer.valueOf(certificate.getCertificateLevel())) {
                throw new RuntimeException("存在下级证书不能删除");
            }
            return certificate;
        }).collect(Collectors.toList());
        int result = pmsCertificateService.batchDelete(pmsCertificates);
        return handleResult(result);
    }
}

