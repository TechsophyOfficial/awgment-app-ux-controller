package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comments DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO
{
    private String taskId;

    private String processInstanceId;

    private String businessKey;

    private String comment;
}
