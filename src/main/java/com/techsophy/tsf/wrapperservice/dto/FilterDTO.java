package com.techsophy.tsf.wrapperservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO
{
    private  String resourceType;
    private  String name;
    private  String owner;
    Map<String,Object> query;
    Map<String,Object> properties;
}
