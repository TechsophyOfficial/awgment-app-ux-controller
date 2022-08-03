package com.techsophy.tsf.wrapperservice.dto;

import lombok.Value;

@Value
public class VariableDTO
{
    String name;
    Object value;
    String operator;
}
