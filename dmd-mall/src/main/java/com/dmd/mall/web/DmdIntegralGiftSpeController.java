package com.dmd.mall.web;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.DmdIntegralGiftSpeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/dmdIntegralGiftSpe")
@Api(tags = "DmdIntegralGiftSpeController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DmdIntegralGiftSpeController extends BaseController {

    @Autowired
    private DmdIntegralGiftSpeService dmdIntegralGiftSpeService;


}

