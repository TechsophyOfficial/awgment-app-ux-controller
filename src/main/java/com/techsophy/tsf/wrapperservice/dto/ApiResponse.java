package com.techsophy.tsf.wrapperservice.dto;

import lombok.Value;
import lombok.With;

/**
 * api response
 * @param <T>
 */
@Value
@With
public class ApiResponse<T>
{
    T data;
    Boolean success;
    String message;
}
