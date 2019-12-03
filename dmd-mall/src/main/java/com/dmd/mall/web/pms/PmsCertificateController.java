package com.dmd.mall.web.pms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.PmsCertificateVo;
import com.dmd.mall.service.PmsCertificateService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/certificateList/find")
    @ApiOperation(httpMethod = "GET", value = "查询所有的证书信息")
    public Wrapper findCertificateList() {
        List<PmsCertificateVo> pmsCertificates = pmsCertificateService.selectCertificateList();
        return WrapMapper.ok(pmsCertificates);
    }
}

