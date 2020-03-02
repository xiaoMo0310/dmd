package com.dmd.admin.model.domain;


import com.dmd.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import javax.persistence.Column;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 教练店铺店铺表
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_shop_level_explain")
@Alias(value = "UmsShopLevelExplain")
@ApiModel
public class UmsShopLevelExplain extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "level_explain")
    @ApiModelProperty("店铺等级说明")
    private String levelExplain;


}
