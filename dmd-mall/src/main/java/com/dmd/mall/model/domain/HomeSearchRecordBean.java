package com.dmd.mall.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: HomeSearchRecordBean
 * @projectName dmd-masters
 * @description: TODO 首页--搜索 历史记录表
 * @date 2019/10/1818:19
 */
public class HomeSearchRecordBean {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 搜索内容
     */
    private String name;
    /**
     * 搜索时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    /**
     * 搜索类型 1.动态 2.商品 3.话题
     */
    private Integer searchType;
    /**
     * 用户id
     */
    private Long userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "HomeSearchRecordBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", searchType=" + searchType +
                ", userid=" + userid +
                '}';
    }
}
