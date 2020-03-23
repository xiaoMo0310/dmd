package com.dmd.mall.model.vo;

public class PowerNotesMemberVo {

    private String nickName;

    private String phone;

    private Long userId;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PowerNotesMemberVo{" +
                "nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", userId=" + userId +
                '}';
    }
}
