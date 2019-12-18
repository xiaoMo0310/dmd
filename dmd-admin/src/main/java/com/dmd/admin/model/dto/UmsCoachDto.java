package com.dmd.admin.model.dto;

import lombok.Data;

@Data
public class UmsCoachDto {

    private Long coachId;

    private Integer status;

    private String failureReason;
}
