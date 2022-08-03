package com.techsophy.tsf.wrapperservice.exception;

public class MissingMandatoryDataException extends RuntimeException
{
    String errorCode;
    String message;
    public MissingMandatoryDataException(String errorcode,String message)
    {
        super(message);
        this.errorCode =errorcode;
        this.message=message;
    }
}
