package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionsDTO
{
    private String label;
    private String url;
    private String action;
    private String actionType;
}
