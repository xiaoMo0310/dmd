package com.dmd.admin.web;

import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.model.domain.PmsComment;
import com.dmd.admin.service.PmsCommentService;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: PmsCommentController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2118:30
 */
@Controller
@Api(tags = "PmsCommentController", description = "后台--买家评论")
@RequestMapping("/pmsComment")
public class PmsCommentController {

    @Autowired
    private PmsCommentService pmsCommentService;

    /**
     * 分页查询买家评论条查
     * @param pageNum
     * @param pageSize
     * @param pmsComment
     * @return
     */
    @ApiOperation("分页查询买家评论/条查")
    @RequestMapping(value = "/selectCommentAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsComment>> queryCommentAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize, PmsComment pmsComment) {
        List<PmsComment> CommentList = pmsCommentService.queryCommentAll(pageNum,pageSize,pmsComment);
        return CommonResult.success(CommentList);
    }

    /**
     * 批量删除买家评论
     * @param ids
     * @return
     */
    @ApiOperation("批量删除买家评论")
    @RequestMapping(value = "/deleteComment",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteComment(@RequestBody String... ids){
        int count = pmsCommentService.deleteComment(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
