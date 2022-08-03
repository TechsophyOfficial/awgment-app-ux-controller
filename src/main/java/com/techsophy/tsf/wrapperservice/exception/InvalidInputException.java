package com.techsophy.tsf.wrapperservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class InvalidInputException extends RuntimeException
{
    String errorCode;
    String message;
    public InvalidInputException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
        this.message=message;
    }
}


