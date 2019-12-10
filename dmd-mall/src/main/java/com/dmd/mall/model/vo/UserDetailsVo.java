package com.dmd.mall.model.vo;

/**
 * @author ChenYanbing
 * @title: UserDetailsVo
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/99:58
 */
public class UserDetailsVo {

    /**
     * userId
     */
    private Long id;

    /**
     * 会员等级
     */
    private Long memberLevelId;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String icon;
    /**
     * 用户图集
     */
    private String pics;
    /**
     * 登录人是否关注动态发布用户关注人(0==未关注)(1==已关注)
     */
    private Integer Identification;

    /**
     * 个人简介
     */
    private String personalizedSignature;

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Long memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public Integer getIdentification() {
        return Identification;
    }

    public void setIdentification(Integer identification) {
        Identification = identification;
    }

    @Override
    public String toString() {
        return "UserDetailsVo{" +
                "id=" + id +
                ", memberLevelId=" + memberLevelId +
                ", nickname='" + nickname + '\'' +
                ", icon='" + icon + '\'' +
                ", pics='" + pics + '\'' +
                ", Identification=" + Identification +
                ", personalizedSignature='" + personalizedSignature + '\'' +
                '}';
    }
}
