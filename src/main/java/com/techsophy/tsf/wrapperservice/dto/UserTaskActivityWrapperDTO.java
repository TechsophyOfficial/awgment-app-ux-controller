package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTaskActivityWrapperDTO
{
    private String taskId;
    private String assignee;
}
