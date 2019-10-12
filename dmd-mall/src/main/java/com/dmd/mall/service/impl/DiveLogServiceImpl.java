package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DiveLogMapper;
import com.dmd.mall.model.domain.DiveLogAirbottleBean;
import com.dmd.mall.model.domain.DiveLogAndAirbottle;
import com.dmd.mall.model.domain.DiveLogBean;
import com.dmd.mall.service.DiveLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: DiveLogServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1113:49
 */
@Service
public class DiveLogServiceImpl implements DiveLogService{

    @Autowired
    private DiveLogMapper diveLogMapper;

    @Override
    public List<DiveLogBean> queryDiveLogAll(Long userId) {
        return diveLogMapper.queryDiveLogAll(userId);
    }

    @Override
    public List<DiveLogBean> queryDiveLogById(Long id) {
        return diveLogMapper.queryDiveLogById(id);
    }

    @Override
    public List<DiveLogAirbottleBean> queryDiveLogAirbottleByDiveLogId(Long id) {
        return diveLogMapper.queryDiveLogAirbottleByDiveLogId(id);
    }

    @Override
    public int addDiveLog(DiveLogAndAirbottle diveLogAndAirbottle) {
        //潜水日志实体
        DiveLogBean diveLogBean = diveLogAndAirbottle.getDiveLogBean();
        diveLogBean.setCreatetime(new Date());
        diveLogBean.setDelflag(0);
        diveLogBean.setPraiseNum(0);
        diveLogBean.setCommentNum(0);
        diveLogBean.setShareNum(0);
        diveLogMapper.addDiveLog(diveLogBean);
        //获取到新增完成之后的日志Id
        Long id = diveLogBean.getId();
        System.out.println(id);
        //潜水气瓶消耗实体
        List<DiveLogAirbottleBean> diveLogAirbottleList = diveLogAndAirbottle.getDiveLogAirbottleList();
        //循环赋值
        for (int i = 0; i < diveLogAirbottleList.size(); i++) {
            diveLogAirbottleList.get(i).setDiveLogId(id);
            diveLogAirbottleList.get(i).setDelflag(0);
            diveLogAirbottleList.get(i).setResidue(diveLogAirbottleList.get(i).getStart() - diveLogAirbottleList.get(i).getEnd());
        }
        System.out.println(diveLogAirbottleList);
        return diveLogMapper.addDiveLogAirbottle(diveLogAirbottleList);
    }
}
