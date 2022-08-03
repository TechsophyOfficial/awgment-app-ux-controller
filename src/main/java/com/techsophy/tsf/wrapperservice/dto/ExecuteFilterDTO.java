package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteFilterDTO
{
    List<Map<String,Object>> sorting;
}
