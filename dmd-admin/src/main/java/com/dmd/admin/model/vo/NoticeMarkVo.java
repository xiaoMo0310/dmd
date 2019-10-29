package com.dmd.admin.model.vo;

import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/29 16:43
 * @Description 用户通知标记 vo
 */
@Data
public class NoticeMarkVo {

    private Long userId;

    private String userName;

    private Integer isRead;

    private String readTime;
}
