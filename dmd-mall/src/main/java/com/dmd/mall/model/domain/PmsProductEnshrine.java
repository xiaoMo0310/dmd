package com.dmd.mall.model.domain;

import java.util.Date;
import com.dmd.core.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import javax.persistence.Column;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 商品店铺收藏表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "pms_product_enshrine")
@Alias(value = "PmsProductEnshrine")
@ApiModel
public class PmsProductEnshrine extends BaseEntity {

private static final long serialVersionUID = 1L;
    @Column(name = "member_id")
    @ApiModelProperty("用户id")
    private Long memberId;

    @Column(name = "product_id")
    @ApiModelProperty("商品id")
    private Long productId;

    @Column(name = "shop_id")
    @ApiModelProperty("店铺id")
    private Long shopId;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("")
    private Date createTime;


}
