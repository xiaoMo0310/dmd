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
 * 用户通告表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_notice")
@Alias(value = "UmsNotice")
@ApiModel
public class UmsNotice extends BaseEntity {

private static final long serialVersionUID = 1L;


    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @Column(name = "is_cancel")
    @ApiModelProperty("是否撤销(0:撤销 1:未撤销)")
    private Integer isCancel;

    @Column(name = "is_delete")
    @ApiModelProperty("是否删除(0:删除 1:未删除)")
    private Integer isDelete;

    @ApiModelProperty("通告对象类型(1:单个用户 2:多个用户 3:全部用户 )")
    private Integer type;

    @Column(name = "message_type")
    @ApiModelProperty("消息类型(1:系统消息)")
    private Integer messageType;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型(member:普通用户 coach:教练)")
    private String userType;


}
