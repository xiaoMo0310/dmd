package com.dmd.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/7 16:40
 * @Description 教练信息 vo
 */
@Data
public class UmsCoachVo implements Serializable {

    private static final long serialVersionUID = 6050922373075093812L;
    /**
     * 教练id
     */
    private Long id;

    /**
     * 教练名称
     */
    private String coachName;

    /**
     * 教练昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String icon;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 教练等级
     */
    private String coachGrade;

    /**
     * 个人简介
     */
    private String personalProfile;

    /**
     * 个人影集
     */
    private String personalAlbum;

    /**
     * 教练邀请码
     */
    private String invitationCode;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 帐号启用状态:0->禁用；1->待审核; 2->正常
     */
    private Integer status;

    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;

    /**
     * 教练证书图片
     */
    private String certificatePic;

    /**
     * 审核未通过原因
     */
    private String failureReason;

}
