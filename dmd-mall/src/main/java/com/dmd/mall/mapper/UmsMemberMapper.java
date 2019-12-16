package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.domain.UmsMemberExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper
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

    Set<String> getPermission(@Param("username")String username);

    String getCoachUser(@Param("invitationCode") String invitationCode);

    Integer queryMyIntegral(Long userId);

    UmsMember getByUsernameCoach(@Param("username") String username);

    int insertCoach(UmsMember umsMember);
    int updatePhone(@Param("phone") String phone,@Param("username") String username);

    int verifiedInvitationCode(String invitationCode);

    UmsMember selectById(Long userId);

    UmsMember selectByUserName(String loginName);
}