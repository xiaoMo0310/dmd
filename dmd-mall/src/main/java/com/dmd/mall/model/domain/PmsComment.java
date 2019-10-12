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
 * 商品评价表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_comment")
@Alias(value = "PmsComment")
@ApiModel
public class PmsComment extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

    @Column(name = "product_name")
    @ApiModelProperty("商品名称")
    private String productName;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "member_nick_name")
    @ApiModelProperty("用户昵称")
    private String memberNickName;

    @Column(name = "member_icon")
    @ApiModelProperty("评论用户头像")
    private String memberIcon;

    @ApiModelProperty("评价星数：0->5")
    private Integer star;

    @Column(name = "member_ip")
    @ApiModelProperty("评价的ip")
    private String memberIp;

    @Column(name = "show_status")
    @ApiModelProperty("显示状态")
    private Integer showStatus;

    @Column(name = "product_attribute")
    @ApiModelProperty("购买时的商品属性")
    private String productAttribute;
    @Column(name = "read_count")
    @ApiModelProperty("")
    private Integer readCount;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("上传图片地址，以逗号隔开")
    private String pics;


}
