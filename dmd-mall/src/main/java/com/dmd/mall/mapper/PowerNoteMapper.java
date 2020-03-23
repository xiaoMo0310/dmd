package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PowerNotesBean;
import com.dmd.mall.model.vo.PowerNotesMemberVo;

import java.util.List;

public interface PowerNoteMapper {
    PowerNotesBean selectPowerNotesById(Long id);

    int updatePowerNotesById(PowerNotesBean powerNotesBean);

    int addPowerNote(PowerNotesBean powerNotesBean);

    PowerNotesMemberVo selectPowerNotesMemberList(Long integer);
}
