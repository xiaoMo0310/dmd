package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/31 9:17
 * @Description 用户通知 vo
 */
@Data
public class UmsNoticeVo {

    /**
     * 消息id
     */
    private Long noticeId;

    /**
     * 是否阅读
     */
    private Integer isRead;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 发送日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 来源用户id
     */
    private Long userId;

    /**
     * 头像
     */
    private String icon;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 跳转地址
     */
    private String jumpAddress;


}
