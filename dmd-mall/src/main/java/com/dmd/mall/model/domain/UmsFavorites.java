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
 *  
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_favorites")
@Alias(value = "UmsFavorites")
@ApiModel
public class UmsFavorites extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "favorite_type")
    @ApiModelProperty("关注类型(0:用户 1:商品 2:店铺)")
    private Integer favoriteType;

    @Column(name = "target_id")
    @ApiModelProperty("对象id")
    private Long targetId;


}
