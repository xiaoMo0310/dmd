package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/30 15:14
 * @Description 通知消息是否已读 vo
 */
@Data
public class UmsNoticeReadVo implements Serializable {

    private static final long serialVersionUID = -7216168301711877747L;
    /**
     * 消息类型(1:系统消息 2:点赞消息 3:评论消息)
     */
    private Integer messageType;

    /**
     * 是否有未读信息(0:没有 1:有)
     */
    private Integer isRead;

    /**
     * 未读数量
     */
    private Long count;
}
