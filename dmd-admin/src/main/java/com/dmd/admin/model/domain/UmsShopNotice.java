package com.dmd.admin.model.domain;

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
 * 教练店铺公告
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_shop_notice")
@Alias(value = "UmsShopNotice")
@ApiModel
public class UmsShopNotice extends BaseEntity {

private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("公告内容")
    private String content;

    @Column(name = "is_delete")
    @ApiModelProperty("是否删除")
    private Integer isDelete;


}
