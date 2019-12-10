package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/22 13:58
 * @Description 商品评价 vo
 */
@Data
public class ProductAppraiseVo {

    /**
     *用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 评价时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 评论内容
     */
    private String info;

    /**
     * 级别 -1差评 0中评 1好评
     */
    private String level;

    /**
     * 描述相符 1-5
     */
    private Integer descStar;

    /**
     * 物流服务 1-5
     */
    private Integer logisticsStar;

    /**
     * 服务态度 1-5
     */
    private Integer attitudeStar;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 评价图片
     */
    private List picList = new ArrayList<>(0);
}
