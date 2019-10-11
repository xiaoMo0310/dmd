package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.DiveLogMapper;
import com.dmd.mall.service.DiveLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
