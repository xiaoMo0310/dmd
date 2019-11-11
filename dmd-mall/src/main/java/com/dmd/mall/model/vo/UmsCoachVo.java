package com.dmd.mall.model.vo;

import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/7 16:40
 * @Description 教练信息 vo
 */
@Data
public class UmsCoachVo {

    /**
     * 教练id
     */
    private Long id;

    /**
     * 教练名称
     */
    private String coachName;

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
     * 教练邀请码
     */
    private String invitationCode;

}
