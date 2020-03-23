package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PowerNotesBean;
import com.dmd.mall.model.vo.PowerNotesMemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PowerNoteMapper {
    PowerNotesBean selectPowerNotesById(Long id);

    int updatePowerNotesById(PowerNotesBean powerNotesBean);

    int addPowerNote(PowerNotesBean powerNotesBean);

    PowerNotesMemberVo selectPowerNotesMemberList(Long integer);
}
