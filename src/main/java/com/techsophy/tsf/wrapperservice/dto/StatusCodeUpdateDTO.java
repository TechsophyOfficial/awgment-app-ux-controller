package com.techsophy.tsf.wrapperservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCodeUpdateDTO
{
    String id;
    Map<String,Object> formData;
}
