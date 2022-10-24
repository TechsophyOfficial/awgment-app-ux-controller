package com.techsophy.tsf.wrapperservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusCodeUpdateDTO
{
    private String formId;
    private String id;
    private Map<String,Object> formData;
}
