package com.dmd.mall.model.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
/**
 * @author YangAnsheng
 */
@Data
public class UmsMember implements Serializable {
    private Long id;

    private Long memberLevelId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "所做城市")
    private String city;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "个性签名")
    private String personalizedSignature;

    @ApiModelProperty(value = "用户来源")
    private Integer sourceType;

    @ApiModelProperty(value = "积分")
    private Integer integration;

    @ApiModelProperty(value = "成长值")
    private Integer growth;

    @ApiModelProperty(value = "剩余抽奖次数")
    private Integer luckeyCount;

    @ApiModelProperty(value = "历史积分数量")
    private Integer historyIntegration;

    @ApiModelProperty(value = "身份证")
    private String identityCard;

    @ApiModelProperty(value = "身高")
    private String stature;

    @ApiModelProperty(value = "体重")
    private String weight;

    @ApiModelProperty(value = "角色")
    private String role;

    @Column(name = "open_id")
    @ApiModelProperty("微信")
    private String openId;

    @Column(name = "coach_name")
    @ApiModelProperty("教练名称")
    private String coachName;

    @Column(name = "nick_name")
    @ApiModelProperty("昵称")
    private String nickName;

    @Column(name = "coach_grade")
    @ApiModelProperty("教练等级")
    private String coachGrade;

    @Column(name = "invitation_code")
    @ApiModelProperty("邀请码")
    private String invitationCode;

    @Column(name = "total_invitation")
    @ApiModelProperty("邀请总的人数")
    private Integer totalInvitation;

    @Column(name = "personal_album")
    @ApiModelProperty("个人影集")
    private String personalAlbum;

    @Column(name = "personal_profile")
    @ApiModelProperty("个人简介")
    private String personalProfile;

    /**
     * 用户类型
     */
    private String loginType;


    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "UmsMember{" +
                "id=" + id +
                ", memberLevelId=" + memberLevelId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", icon='" + icon + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", city='" + city + '\'' +
                ", job='" + job + '\'' +
                ", personalizedSignature='" + personalizedSignature + '\'' +
                ", sourceType=" + sourceType +
                ", integration=" + integration +
                ", growth=" + growth +
                ", luckeyCount=" + luckeyCount +
                ", historyIntegration=" + historyIntegration +
                ", identityCard='" + identityCard + '\'' +
                ", stature='" + stature + '\'' +
                ", weight='" + weight + '\'' +
                ", role='" + role + '\'' +
                ", openId='" + openId + '\'' +
                ", coachName='" + coachName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", coachGrade='" + coachGrade + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", totalInvitation=" + totalInvitation +
                ", personalAlbum='" + personalAlbum + '\'' +
                ", personalProfile='" + personalProfile + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }
}