package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

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
     * 教练证书图片
     */
    private String certificatePic;

    /**
     * 邀请总数
     */
    private Long totalInvitations;

    /**
     *关注总数
     */
    private Integer totalFollow;

    /**
     * 发布商品总数
     */
    private Long totalProduct;

}
