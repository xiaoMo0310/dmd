package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsDict;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-27
 */
@Mapper
@Component
public interface PmsDictMapper extends MyMapper<PmsDict> {

}
