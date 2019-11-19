package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.HomeSearchRecordBean;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.service.HomeSearchService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: HomeSearchController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1818:12
 */
@Controller
@Api(tags = "HomeSearchController", description = "首页--搜索")
@RequestMapping("/search")
public class HomeSearchController {

    @Autowired
    private HomeSearchService homeSearchService;

    /**
     * 查询全部我的动态
     * @param userId
     * @return
     */
    @ApiOperation("首页--搜索动态/商品/话题")
    @RequestMapping(value = "/searchCount",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo> querycontent(@RequestParam String content ,@RequestParam Long userId,@RequestParam Integer searchType,@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize) {
        List list = new ArrayList<>();
        if(searchType == 1){
            List<DynamicBean> dynamicList = homeSearchService.queryDynamic(userId,content,searchType,pageNum,pageSize);
            list=dynamicList;
        }
        if(searchType == 2){
            //List<PmsProduct> pmsProductList = homeSearchService.queryPmsProduct(userId,content,searchType,pageNum,pageSize);
            List<PmsProduct> pmsProductList = homeSearchService.queryPmsCourseProduct(userId,content,searchType,pageNum,pageSize);
            list=pmsProductList;
        }
        if(searchType == 3){
            List<TopicBean> TopicList = homeSearchService.queryTopic(userId,content,searchType,pageNum,pageSize);
            list=TopicList;
        }
        return CommonResult.success(new PageInfo<>(list));
    }


    /**
     * 查询历史记录
     * @param userId
     * @return
     */
    @ApiOperation("查询历史搜索")
    @RequestMapping(value = "/queryHistory",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<HomeSearchRecordBean>> queryHistory(@RequestParam Long userId) {
        List<HomeSearchRecordBean> historyList = homeSearchService.queryHistory(userId);
        return CommonResult.success(historyList);
    }


    /**
     * 删除历史记录
     * @param userid
     * @return
     */
    @ApiOperation("删除历史记录")
    @RequestMapping(value = "/deleteHistoryByUserid",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteHistoryByUserid(@RequestParam Long userId){
        int count = homeSearchService.deleteHistoryByUserid(userId);
        if (count > 0) {
            return CommonResult.success(count,"清除历史记录成功");
        }
        return CommonResult.failed("清除历史记录失败");
    }
}
