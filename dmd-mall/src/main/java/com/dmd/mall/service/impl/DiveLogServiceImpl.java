package com.dmd.mall.service.impl;

import com.dmd.WordFilter;
import com.dmd.mall.mapper.DiveLogCommentMapper;
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

    @Autowired
    private DiveLogCommentMapper diveLogCommentMapper;

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
        //敏感词过滤*****
        String content = WordFilter.doFilter(diveLogBean.getDivingIdea());
        System.out.println(content);
        diveLogBean.setDivingIdea(content);
        diveLogMapper.addDiveLog(diveLogBean);
        //获取到新增完成之后的日志Id
        Long diveLogId = diveLogBean.getId();
        System.out.println(diveLogId);
        //潜水气瓶消耗实体
        List<DiveLogAirbottleBean> diveLogAirbottleList = diveLogAndAirbottle.getDiveLogAirbottleList();
        //循环赋值
        for (int i = 0; i < diveLogAirbottleList.size(); i++) {
            diveLogAirbottleList.get(i).setDiveLogId(diveLogId);
            diveLogAirbottleList.get(i).setDelflag(0);
            //剩余气瓶量
            diveLogAirbottleList.get(i).setResidue(diveLogAirbottleList.get(i).getStart() - diveLogAirbottleList.get(i).getEnd());
        }
        System.out.println(diveLogAirbottleList);
        //新增气瓶消耗表
        return diveLogMapper.addDiveLogAirbottle(diveLogAirbottleList);
    }

    @Override
    public int updateLikePraise(Long id) {
        return diveLogMapper.updateLikePraise(id);
    }

    @Override
    public int updateCancelPraise(Long id) {
        return diveLogMapper.updateCancelPraise(id);
    }

    @Override
    public Integer queryPraise(Long id) {
        return diveLogMapper.queryPraise(id);
    }

    @Override
    public int updateDiveLogShare(Long id) {
        return diveLogMapper.updateDiveLogShare(id);
    }

    @Override
    public Integer queryShare(Long id) {
        return diveLogMapper.queryShare(id);
    }

    @Override
    public int updateDiveLogDelflag(Long id) {

        //删除潜水日志
        diveLogMapper.updateDiveLogDelflag(id);
        //删除潜水日志对应的气瓶消耗删除
        diveLogMapper.updateDiveLogAirbottleDelflag(id);
        //删除潜水日志对应日志下评论删除
        return diveLogCommentMapper.updateDiveLogDelflag(id);
    }
}
