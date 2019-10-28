package com.dmd.admin.web;

import com.dmd.admin.model.domain.CommentBean;
import com.dmd.admin.model.domain.PmsComment;
import com.dmd.admin.service.PmsCommentService;
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
    public CommonResult<CommonPage<PmsComment>> queryCommentAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                PmsComment pmsComment) {
        if(pmsComment.getStratTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(pmsComment.getStratTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "00");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                pmsComment.setStratTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(pmsComment.getEndTime() != null){
            String time = "";
            String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(pmsComment.getEndTime());
            if(StringUtils.isNotBlank(dateStr)){
                StringBuilder sb = new StringBuilder(dateStr);
                sb.replace(11, 13, "24");
                time = sb.toString();
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(time);
                pmsComment.setEndTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<PmsComment> CommentList = pmsCommentService.queryCommentAll(pageNum,pageSize,pmsComment);
        return CommonResult.success(CommonPage.restPage(CommentList));
    }

    /**
     * 批量删除买家评论
     * @param ids
     * @return
     */
    @ApiOperation("批量删除买家评论")
    @RequestMapping(value = "/deleteComment",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteComment(@RequestParam("ids") List<Long> ids){
        int count = pmsCommentService.deleteComment(ids);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }
}
