package com.techsophy.tsf.wrapperservice.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FilterResponseDTO
{
    private String id;
    private  String resourceType;
    private  String name;
    private  String owner;
    Map<String,Object> query;
    Map<String,Object> properties;

}
