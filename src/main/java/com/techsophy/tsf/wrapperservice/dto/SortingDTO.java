package com.techsophy.tsf.wrapperservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import java.util.Map;

@Value
public class SortingDTO
{
    String sortBy;
    String sortOrder;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Map<String, Object> parameters;
}
