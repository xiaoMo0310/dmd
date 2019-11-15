package com.dmd.mall.model.domain;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsSpeBean
 * @projectName dmd
 * @description: TODO
 * @date 2019/11/1515:19
 */
public class IntegralGiftsSpeBean {

    private Long id;

    private Long giftId;

    private Integer specStock;

    private String size;

    private String color;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Integer getSpecStock() {
        return specStock;
    }

    public void setSpecStock(Integer specStock) {
        this.specStock = specStock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "IntegralGiftsSpeBean{" +
                "id=" + id +
                ", giftId=" + giftId +
                ", specStock=" + specStock +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
