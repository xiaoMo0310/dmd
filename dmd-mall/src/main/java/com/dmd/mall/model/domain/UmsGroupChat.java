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
 * 教练群聊创建 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_group_chat")
@Alias(value = "UmsGroupChat")
@ApiModel
public class UmsGroupChat extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "founder_account")
    @ApiModelProperty("创建群聊账户")
    private String founderAccount;

    @Column(name = "member_account")
    @ApiModelProperty("群聊成员账户")
    private String memberAccount;

    @ApiModelProperty("状态(0:未创建 1:创建成功)")
    private Integer status;

    @Column(name = "is_first")
    @ApiModelProperty("是否是首位成员(0:不是 1:是)")
    private Integer isFirst;


}
