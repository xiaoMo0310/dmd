package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * <p>
 * 用户通知标记表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_notice_mark")
@Alias(value = "UmsNoticeMark")
@ApiModel
public class UmsNoticeMark extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "notice_id")
    @ApiModelProperty("通告id")
    private Long noticeId;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型(1:普通用户)")
    private Integer userType;

    @Column(name = "is_read")
    @ApiModelProperty("是否阅读(0:未阅读 1:阅读)")
    private Integer isRead;

    @Column(name = "read_time")
    @ApiModelProperty("阅读时间(分钟)")
    private String readTime;


}
