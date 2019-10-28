package com.dmd.admin.web;

import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.service.CommentService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentController
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2413:34
 */
@Controller
@Api(tags = "CommentController", description = "后台--动态/日志评论")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 分页查询动态日志下评论
     * @param pageNum
     * @param pageSize
     * @param commentBean
     * @return
     */
    @ApiOperation("分页查询动态日志下评论/条查")
    @RequestMapping(value = "/selectCommentAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CommentBean>> queryCommentAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 CommentBean commentBean) {
        if(commentBean.getStratTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(commentBean.getStratTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                commentBean.setStratTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(commentBean.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(commentBean.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                commentBean.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<CommentBean> CommentList = commentService.queryCommentAll(pageNum,pageSize,commentBean);
        return CommonResult.success(CommonPage.restPage(CommentList));
    }


    /**
     * 批量删除动态或者日志评论
     * @param ids
     * @return
     */
    @ApiOperation("批量删除动态或者日志评论")
    @RequestMapping(value = "/updateCommentDelflag",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCommentDelflag(@RequestParam("ids") List<Long> ids){
        int count = commentService.updateCommentDelflag(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

}
