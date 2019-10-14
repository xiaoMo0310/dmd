package com.dmd.mall.model.domain;

import io.swagger.models.auth.In;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: DiveLogBean
 * @projectName dmd-masters
 * @description: TODO 潜水日志表
 * @date 2019/10/119:55
 */
public class DiveLogBean {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 潜水次数
     */
    private Integer divingNumber;
    /**
     * 最大深度
     */
    private Integer maximumDepth;
    /**
     * 平均深度
     */
    private Integer average_depth;
    /**
     * 深度类型(0=m/1=ft)
     */
    private Integer depthType;
    /**
     * 最低温度
     */
    private Integer minimumTemperature;
    /**
     * 平均温度
     */
    private Integer average_temperature;
    /**
     * 温度类型(0=℃/1=℉)
     */
    private Integer temperatureType;
    /**
     * 开始潜水时间
     */
    private Date startTime;
    /**
     * 结束潜水时间
     */
    private Date endTime;
    /**
     * 潜水地点
     */
    private String diveSites;
    /**
     * 潜水想法
     */
    private String divingIdea;
    /**
     * 潜水照片
     */
    private String divingPictures;
    /**
     * 潜水配重
     */
    private Integer additionalWeight;
    /**
     * 入水方式
     */
    private String waterWay;
    /**
     * 景物
     */
    private String scenery;
    /**
     * 天气
     */
    private String weather;
    /**
     * 水流
     */
    private String flow;
    /**
     * 能见度
     */
    private Integer visibility;
    /**
     * 动物照片
     */
    private String animalPhotos;
    /**
     * 湿衣品牌
     */
    private String clothingBrand;
    /**
     * BCD品牌
     */
    private String airbottleBrand;
    /**
     * 面镜品牌
     */
    private String maskBrand;
    /**
     * 手表品牌
     */
    private String watchesBrand;
    /**
     * 相机品牌
     */
    private String cameraBrand;
    /**
     * 脚蹼品牌
     */
    private String finsBrand;
    /**
     * 发布时间
     */
    private Date createtime;
    /**
     * 编辑时间
     */
    private Date edittime;
    /**
     * 逻辑删除(0=正常/1=删除)
     */
    private Integer delflag;
    /**
     * 发布人/作者/登录人
     */
    private String author;
    /**
     * 用户头像
     */
    private String userHeadPortrait;
    /**
     * 关联用户表，关联用户id
     */
    private Long userId;
    /**
     * 点赞数(默认0)
     */
    private Integer praiseNum;
    /**
     * 分享数(默认0)
     */
    private Integer shareNum;
    /**
     * 评论数(默认0)
     */
    private Integer commentNum;
    /**
     * 潜水类型(0=水肺潜水，1=自由潜水)
     */
    private Integer diveType;

    public Integer getDiveType() {
        return diveType;
    }

    public void setDiveType(Integer diveType) {
        this.diveType = diveType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDivingNumber() {
        return divingNumber;
    }

    public void setDivingNumber(Integer divingNumber) {
        this.divingNumber = divingNumber;
    }

    public Integer getMaximumDepth() {
        return maximumDepth;
    }

    public void setMaximumDepth(Integer maximumDepth) {
        this.maximumDepth = maximumDepth;
    }

    public Integer getAverage_depth() {
        return average_depth;
    }

    public void setAverage_depth(Integer average_depth) {
        this.average_depth = average_depth;
    }

    public Integer getDepthType() {
        return depthType;
    }

    public void setDepthType(Integer depthType) {
        this.depthType = depthType;
    }

    public Integer getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(Integer minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public Integer getAverage_temperature() {
        return average_temperature;
    }

    public void setAverage_temperature(Integer average_temperature) {
        this.average_temperature = average_temperature;
    }

    public Integer getTemperatureType() {
        return temperatureType;
    }

    public void setTemperatureType(Integer temperatureType) {
        this.temperatureType = temperatureType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDiveSites() {
        return diveSites;
    }

    public void setDiveSites(String diveSites) {
        this.diveSites = diveSites;
    }

    public String getDivingIdea() {
        return divingIdea;
    }

    public void setDivingIdea(String divingIdea) {
        this.divingIdea = divingIdea;
    }

    public String getDivingPictures() {
        return divingPictures;
    }

    public void setDivingPictures(String divingPictures) {
        this.divingPictures = divingPictures;
    }

    public Integer getAdditionalWeight() {
        return additionalWeight;
    }

    public void setAdditionalWeight(Integer additionalWeight) {
        this.additionalWeight = additionalWeight;
    }

    public String getWaterWay() {
        return waterWay;
    }

    public void setWaterWay(String waterWay) {
        this.waterWay = waterWay;
    }

    public String getScenery() {
        return scenery;
    }

    public void setScenery(String scenery) {
        this.scenery = scenery;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getAnimalPhotos() {
        return animalPhotos;
    }

    public void setAnimalPhotos(String animalPhotos) {
        this.animalPhotos = animalPhotos;
    }

    public String getClothingBrand() {
        return clothingBrand;
    }

    public void setClothingBrand(String clothingBrand) {
        this.clothingBrand = clothingBrand;
    }

    public String getAirbottleBrand() {
        return airbottleBrand;
    }

    public void setAirbottleBrand(String airbottleBrand) {
        this.airbottleBrand = airbottleBrand;
    }

    public String getMaskBrand() {
        return maskBrand;
    }

    public void setMaskBrand(String maskBrand) {
        this.maskBrand = maskBrand;
    }

    public String getWatchesBrand() {
        return watchesBrand;
    }

    public void setWatchesBrand(String watchesBrand) {
        this.watchesBrand = watchesBrand;
    }

    public String getCameraBrand() {
        return cameraBrand;
    }

    public void setCameraBrand(String cameraBrand) {
        this.cameraBrand = cameraBrand;
    }

    public String getFinsBrand() {
        return finsBrand;
    }

    public void setFinsBrand(String finsBrand) {
        this.finsBrand = finsBrand;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserHeadPortrait() {
        return userHeadPortrait;
    }

    public void setUserHeadPortrait(String userHeadPortrait) {
        this.userHeadPortrait = userHeadPortrait;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Override
    public String toString() {
        return "DiveLogBean{" +
                "id=" + id +
                ", divingNumber=" + divingNumber +
                ", maximumDepth=" + maximumDepth +
                ", average_depth=" + average_depth +
                ", depthType=" + depthType +
                ", minimumTemperature=" + minimumTemperature +
                ", average_temperature=" + average_temperature +
                ", temperatureType=" + temperatureType +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", diveSites='" + diveSites + '\'' +
                ", divingIdea='" + divingIdea + '\'' +
                ", divingPictures='" + divingPictures + '\'' +
                ", additionalWeight=" + additionalWeight +
                ", waterWay='" + waterWay + '\'' +
                ", scenery='" + scenery + '\'' +
                ", weather='" + weather + '\'' +
                ", flow='" + flow + '\'' +
                ", visibility=" + visibility +
                ", animalPhotos='" + animalPhotos + '\'' +
                ", clothingBrand='" + clothingBrand + '\'' +
                ", airbottleBrand='" + airbottleBrand + '\'' +
                ", maskBrand='" + maskBrand + '\'' +
                ", watchesBrand='" + watchesBrand + '\'' +
                ", cameraBrand='" + cameraBrand + '\'' +
                ", finsBrand='" + finsBrand + '\'' +
                ", createtime=" + createtime +
                ", edittime=" + edittime +
                ", delflag=" + delflag +
                ", author='" + author + '\'' +
                ", userHeadPortrait='" + userHeadPortrait + '\'' +
                ", userId=" + userId +
                ", praiseNum=" + praiseNum +
                ", shareNum=" + shareNum +
                ", commentNum=" + commentNum +
                ", diveType=" + diveType +
                '}';
    }
}
