package com.dmd.mall.service;

import com.dmd.mall.model.domain.PowerNotesBean;

public interface PowerNoteService {
    PowerNotesBean selectPowerNotesById(Long id);

    int updatePowerNotesById(PowerNotesBean powerNotesBean);

    int addPowerNote(PowerNotesBean powerNotesBean);
}
