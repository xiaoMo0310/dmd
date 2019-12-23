package com.dmd.mall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/15 11:31
 * @Description 潜水商品dto类
 */
@Data
public class CourseProductDto implements Serializable {

    private static final long serialVersionUID = -1130544620904518196L;
    private Long Id;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("卖家id")
    private Long userId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("产品的价格")
    private BigDecimal price;

    @ApiModelProperty("产品类型(1:学证 2:潜水)")
    private Integer productType;

    @ApiModelProperty("地点")
    private String location;

    @ApiModelProperty("游玩时长")
    private String lengthPlay;

    @ApiModelProperty("是否有时间限制(0:无 1:有)")
    private Integer isTimeLimit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("人数是否有限制(0:无 1:有) ")
    private Integer numberIsLimit;

    @ApiModelProperty("人数限制")
    private Integer numberLimit;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("费用包含")
    private String costIncludes;

    @ApiModelProperty("费用不含")
    private String costNotIncludes;

    @ApiModelProperty("购买须知")
    private String purchaseNotes;

    @ApiModelProperty("产品介绍")
    private String productDescription;

    @ApiModelProperty("产品图片(多个使用 , 隔开)")
    private String image;

    @ApiModelProperty("内容安排(数据格式 [{\"date\":\"第1天\",\"message\":\"内容\"},{\"date\":\"第2天\",\"message\":\"内容\"}]")
    private List contentArrangements = new ArrayList<>(0);

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("证书id")
    private Long certificateId;

    @ApiModelProperty("地址id")
    private Long addressId;

}
