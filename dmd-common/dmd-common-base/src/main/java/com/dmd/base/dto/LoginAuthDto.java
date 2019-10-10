package com.dmd.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * The class Login auth dto.
 *
 * @author paascloud.net@gmail.com
 */
@Data
@ApiModel(value = "登录人信息")
public class LoginAuthDto implements Serializable {
	private static final long serialVersionUID = -1137852221455042256L;
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	@ApiModelProperty(value = "登录名")
	private String nickName;
	@ApiModelProperty(value = "用户名")
	private String userName;

	public LoginAuthDto() {
	}

	public LoginAuthDto(Long userId, String userName, String nickName) {
		this.userId = userId;
		this.nickName = nickName;
		this.userName = userName;
	}

}
