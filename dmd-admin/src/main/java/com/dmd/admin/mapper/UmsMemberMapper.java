package com.dmd.admin.mapper;


import com.dmd.admin.model.domain.UmsMember;
import com.dmd.admin.model.domain.UmsMemberExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UmsMemberMapper {
    long countByExample(UmsMemberExample example);

    int deleteByExample(UmsMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(UmsMember record);

    List<UmsMember> selectByExample(UmsMemberExample example);

    UmsMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);

    int updateByExample(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);

    String getCoachUser(@Param("invitationCode") String invitationCode);

    /**
     * 查询当日用户的注册数
     * @return
     */
    List<UmsMember> selectRegisterUser(Date date);

    /**
     * 统计昨日用户访问数量
     * @return
     */
    Long countYesterdayVisitUser();

    /**
     *查询总的人数
     * @return
     */
    Long countTotalUser();
}