package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.HomeSearchRecordBean;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.domain.TopicBean;
import com.dmd.mall.model.vo.PmsCertificateVo;
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
 * @description:
 * @date 2019/10/1818:12
 */
@Controller
@Api(tags = "HomeSearchController", description = "首页--搜索")
@RequestMapping("/search")
public class HomeSearchController {

    @Autowired
    private HomeSearchService homeSearchService;


    /**
     * 首页--搜索动态/商品/话题添加历史搜索记录 改
     * @param content
     * @param userId
     * @param searchType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("首页--搜索动态/商品/话题添加历史搜索记录")
    @RequestMapping(value = "/searchAndAddContent",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo> querycontent(@RequestParam String content ,@RequestParam Long userId,@RequestParam Integer searchType,@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize) {


        List list = new ArrayList<>();
        if(searchType == 1){
            List<DynamicBean> dynamicList = homeSearchService.queryDynamic(userId,content,searchType,pageNum,pageSize);
            list=dynamicList;
        }
        if(searchType == 2){
            //查寻潜水产品产品
            //List<PmsProduct> pmsProductList = homeSearchService.queryPmsProduct(userId,content,searchType,pageNum,pageSize);
            List<PmsCourseProduct> pmsProductList = homeSearchService.queryPmsCourseProduct(userId,content,searchType,pageNum,pageSize);
            /*Map<Integer, List<PmsCourseProduct>> map = new HashMap<>();
            for (PmsCourseProduct pmsProduct : pmsProductList) {
                List<PmsCourseProduct> tmpList = map.get(pmsProduct.getProductType());
                if (tmpList == null) {
                    tmpList = new ArrayList<>();
                    tmpList.add(pmsProduct);
                    map.put(pmsProduct.getProductType(), tmpList);
                } else {
                    tmpList.add(pmsProduct);
                }
            }
            List valuesList = new ArrayList<>(map.values());*/
            list= pmsProductList;
        }
        if(searchType == 3){
            //查寻学证产品
            List<PmsCertificateVo> pmsProductList = homeSearchService.queryPmsCertificate(userId,content,searchType,pageNum,pageSize);
            list= pmsProductList;
        }
        if(searchType == 4){
            List<TopicBean> TopicList = homeSearchService.queryTopic(userId,content,searchType,pageNum,pageSize);
            list=TopicList;
        }

        return CommonResult.success(new PageInfo<>(list));
    }


    /**
     * 首页--搜索动态/商品/话题 改
     * @param content
     * @param userId
     * @param searchType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("首页--搜索动态/商品/话题")
    @RequestMapping(value = "/searchcontent",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PageInfo> searchcontent(@RequestParam String content ,@RequestParam Long userId,@RequestParam Integer searchType,@RequestParam Integer pageNum,
                                               @RequestParam Integer pageSize) {
        List list = new ArrayList<>();
        if(searchType == 1){
            List<DynamicBean> dynamicList = homeSearchService.queryDynamicContent(userId,content,searchType,pageNum,pageSize);
            list=dynamicList;
        }
        if(searchType == 2){
            //List<PmsProduct> pmsProductList = homeSearchService.queryPmsProduct(userId,content,searchType,pageNum,pageSize);
            List<PmsCourseProduct> pmsProductList = homeSearchService.queryPmsCourseProductContent(userId,content,searchType,pageNum,pageSize);
            /*Map<Integer, List<PmsCourseProduct>> map = new HashMap<>();
            for (PmsCourseProduct pmsProduct : pmsProductList) {
                List<PmsCourseProduct> tmpList = map.get(pmsProduct.getProductType());
                if (tmpList == null) {
                    tmpList = new ArrayList<>();
                    tmpList.add(pmsProduct);
                    map.put(pmsProduct.getProductType(), tmpList);
                } else {
                    tmpList.add(pmsProduct);
                }
            }
            List valuesList = new ArrayList<>(map.values());*/
            list= pmsProductList;
        }
        if(searchType == 3){
            //查寻学证产品
            List<PmsCertificateVo> pmsProductList = homeSearchService.queryPmsCertificateCount(userId,content,searchType,pageNum,pageSize);
            list= pmsProductList;
        }
        if(searchType == 4){
            List<TopicBean> TopicList = homeSearchService.queryTopicContent(userId,content,searchType,pageNum,pageSize);
            list=TopicList;
        }

        return CommonResult.success(new PageInfo<>(list));
    }


    /**
     * 查询历史搜索记录 改
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
     * 删除历史记录 改
     * @param userId
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
