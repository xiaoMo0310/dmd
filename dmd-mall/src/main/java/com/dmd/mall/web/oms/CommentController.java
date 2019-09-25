package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: CommentController
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/2413:34
 */
@Controller
@Api(tags = "CommentController", description = "动态评论")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询我的动态下全部的评论
     * @param forDynamicId
     * @return
     */
    @ApiOperation("查询全部我的动态下评论")
    @RequestMapping(value = "/selectCommentAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CommentBean>> queryCommentAll(@RequestParam Long forDynamicId) {
        List<CommentBean> CommentList = commentService.queryCommentAll(forDynamicId);
        return CommonResult.success(CommentList);
    }

    @ApiOperation("新增我的评论/留言")
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addComment(@RequestBody CommentBean commentBean) {
        int count = commentService.addComment(commentBean);
        if (count > 0) {
            return CommonResult.success(count,"评论成功");
        }
        return CommonResult.failed("评论失败");
    }

    @ApiOperation("新增评论回复")
    @RequestMapping(value = "/addCommentReply",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addCommentReply(@RequestBody CommentBean commentBean,
                                        //回复的评论的ID
                                        @RequestParam Long commentId,
                                        //被回复者的ID
                                        @RequestParam Long forUid

    ) {
        int count = commentService.addCommentReply(commentBean,commentId,forUid);
        if (count > 0) {
            return CommonResult.success(count,"回复成功");
        }
        return CommonResult.failed("回复失败");
    }
}
