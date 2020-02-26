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
 * 教练店铺自动回复
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_shop_reply")
@Alias(value = "UmsShopReply")
@ApiModel
public class UmsShopReply extends BaseEntity {

    private static final long serialVersionUID = 8988538188293779007L;
    @Column(name = "coach_id")
    @ApiModelProperty("教练id")
    private Long coachId;

    @Column(name = "shop_id")
    @ApiModelProperty("商铺id")
    private Long shopId;

    @Column(name = "question_name")
    @ApiModelProperty("问题名称")
    private String questionName;

    @Column(name = "question_answering")
    @ApiModelProperty("问题回答")
    private String questionAnswering;

    @Column(name = "is_delete")
    @ApiModelProperty("是否删除(1:删除 2:未删除)")
    private Integer isDelete;


}
