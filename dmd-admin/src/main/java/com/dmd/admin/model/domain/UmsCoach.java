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
 * 教练表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_coach")
@Alias(value = "UmsCoach")
@ApiModel
public class UmsCoach extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "open_id")
    @ApiModelProperty("微信")
    private String openId;

    @Column(name = "coach_name")
    @ApiModelProperty("教练名称")
    private String coachName;

    @Column(name = "nick_name")
    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Integer gender;

    @Column(name = "coach_grade")
    @ApiModelProperty("教练等级")
    private String coachGrade;

    @Column(name = "invitation_code")
    @ApiModelProperty("邀请码")
    private String invitationCode;

    @Column(name = "total_invitation")
    @ApiModelProperty("邀请总的人数")
    private Integer totalInvitation;

    @ApiModelProperty("帐号启用状态:0->禁用；1->待审核; 2->正常 3-> 审核未通过")
    private Integer status;

    @Column(name = "personal_album")
    @ApiModelProperty("个人影集")
    private String personalAlbum;

    @Column(name = "personal_profile")
    @ApiModelProperty("个人简介")
    private String personalProfile;

    @ApiModelProperty("成长值")
    private Integer growth;

    @Column(name = "certificate_pic")
    @ApiModelProperty("教练证书图片")
    private String certificatePic;

    @ApiModelProperty("审核未通过原因")
    @Column(name = "failure_reason")
    private String failureReason;

    @ApiModelProperty(value = "积分")
    private Integer integration;

    @ApiModelProperty(value = "历史积分数量")
    @Column(name = "history_integration")
    private Integer historyIntegration;

}
