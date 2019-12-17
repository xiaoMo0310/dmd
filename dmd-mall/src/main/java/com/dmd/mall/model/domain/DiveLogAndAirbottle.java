package com.dmd.mall.model.domain;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogAndAirbottle
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1214:12
 */
public class DiveLogAndAirbottle {

    private DiveLogBean diveLogBean;

    private List<DiveLogAirbottleBean> diveLogAirbottleList;

    public DiveLogBean getDiveLogBean() {
        return diveLogBean;
    }

    public void setDiveLogBean(DiveLogBean diveLogBean) {
        this.diveLogBean = diveLogBean;
    }

    public List<DiveLogAirbottleBean> getDiveLogAirbottleList() {
        return diveLogAirbottleList;
    }

    public void setDiveLogAirbottleList(List<DiveLogAirbottleBean> diveLogAirbottleList) {
        this.diveLogAirbottleList = diveLogAirbottleList;
    }

    @Override
    public String toString() {
        return "DiveLogAndAirbottle{" +
                "diveLogBean=" + diveLogBean +
                ", diveLogAirbottleList=" + diveLogAirbottleList +
                '}';
    }
}
