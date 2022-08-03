package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Data
public class HistoryDTO {
    List<Map<String,Object>> sorting ;
    String taskAssignee;
    boolean finished;
}
