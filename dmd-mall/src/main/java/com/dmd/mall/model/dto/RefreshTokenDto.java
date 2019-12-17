package com.dmd.mall.model.dto;

import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/13 10:32
 * @Description 刷新token dto
 */
@Data
public class RefreshTokenDto {

    private String accessToken;

    private String refreshToken;

    private String loginType;
}
