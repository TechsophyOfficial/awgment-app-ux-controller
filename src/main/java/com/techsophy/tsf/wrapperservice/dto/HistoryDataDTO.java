package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HistoryDataDTO {
    List<Map<String,Object>> sorting ;
    String taskAssignee;
    boolean finished;
}
