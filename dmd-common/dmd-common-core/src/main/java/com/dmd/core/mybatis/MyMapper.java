package com.dmd.core.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/23 11:32
 * @Description 
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
