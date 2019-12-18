package com.dmd.mall.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/13 10:32
 * @Description 刷新token dto
 */
@Data
public class RefreshTokenDto implements Serializable {

    private static final long serialVersionUID = 3051868926440496208L;
    private String accessToken;

    private String refreshToken;

    private String loginType;
}
