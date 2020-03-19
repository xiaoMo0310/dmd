package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/19 17:47
 * @Description 用户 vo类
 */

@Data
public class UmsMemberVo implements Serializable{

    private static final long serialVersionUID = -6801445664595787169L;
    private Long userId;

    private String userIcon;

    private String userNickName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 佣金
     */
    private BigDecimal contribution;
}
