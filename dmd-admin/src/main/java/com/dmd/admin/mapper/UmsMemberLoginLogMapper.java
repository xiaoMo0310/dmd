package com.dmd.admin.mapper;


import com.dmd.admin.model.domain.UmsMemberLoginLog;
import com.dmd.admin.model.domain.UmsMemberLoginLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public interface UmsMemberLoginLogMapper {
    long countByExample(UmsMemberLoginLogExample example);

    int deleteByExample(UmsMemberLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLoginLog record);

    int insertSelective(UmsMemberLoginLog record);

    List<UmsMemberLoginLog> selectByExample(UmsMemberLoginLogExample example);

    UmsMemberLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberLoginLog record, @Param("example") UmsMemberLoginLogExample example);

    int updateByExample(@Param("record") UmsMemberLoginLog record, @Param("example") UmsMemberLoginLogExample example);

    int updateByPrimaryKeySelective(UmsMemberLoginLog record);

    int updateByPrimaryKey(UmsMemberLoginLog record);

    /**
     * 查询用户留存数
     * @param firstDate
     * @param userIds
     * @return
     */
    Map selectRetentionNumber(@Param("firstDate") Date firstDate, @Param("userIds") List<Long> userIds, @Param("userType") String userType);

    /**
     * 查询用户昨日访问量
     * @return
     */
    Long countYesterdayVisitUser(Date date);
}