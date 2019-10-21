package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.service.DiveLogCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogCommentController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1111:31
 */
@Controller
@Api(tags = "DiveLogCommentController", description = "日志评论")
@RequestMapping("/diveLogComment")
public class DiveLogCommentController {

    @Autowired
    private DiveLogCommentService diveLogCommentService;

    /**
     * 查询我的日志下全部的评论
     * @param forDiveLogId
     * @return
     */
    @ApiOperation("查询全部我的日志下评论")
    @RequestMapping(value = "/selectDiveLogCommentAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CommentBean>> queryDiveLogCommentAll(
            //日志ID
            @RequestParam Long forDiveLogId
    ) {
        List<CommentBean> CommentList = diveLogCommentService.queryCommentAll(forDiveLogId);
        return CommonResult.success(CommentList);
    }



    @ApiOperation("新增我的日志评论/留言")
    @RequestMapping(value = "/addDiveLogComment",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addDiveLogComment(@RequestBody CommentBean commentBean) {
        int count = diveLogCommentService.addComment(commentBean);
        if (count > 0) {
            return CommonResult.success(count,"评论成功");
        }
        return CommonResult.failed("评论失败");
    }



    @ApiOperation("新增日志评论回复")
    @RequestMapping(value = "/addDiveLogCommentReply",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addDiveLogCommentReply(@RequestBody CommentBean commentBean,
                                               //回复的评论的ID
                                               @RequestParam Long commentId,
                                               //被回复者的ID
                                               @RequestParam Long forUid

    ) {
        int count = diveLogCommentService.addCommentReply(commentBean,commentId,forUid);
        if (count > 0) {
            return CommonResult.success(count,"回复成功");
        }
        return CommonResult.failed("回复失败");
    }



    /**
     * 我只能删我的回复和评论无法删除别人的回复
     */
    @ApiOperation("逻辑删除日志评论与回复")
    @RequestMapping(value = "/updateDiveLogCommentDelflag",method = RequestMethod.POST)
    @ResponseBody                            //评论id
    public CommonResult updateDiveLogCommentDelflag(@RequestParam Long commentId,
                                                    //登录人id,判断此发布回复和评论的用户是否为是我,发布回复和评论的用户id是否是我的用户id
                                                    @RequestParam Long userId,
                                                    //日志ID
                                                    @RequestParam Long forDiveLogId
    ){
        int count = diveLogCommentService.updateCommentDelflag(commentId,userId,forDiveLogId);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
