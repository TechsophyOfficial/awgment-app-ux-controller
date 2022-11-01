package com.techsophy.tsf.wrapperservice.exception;

public class ResumeProcessException extends RuntimeException{
    final String errorCode;
    final String message;
    public ResumeProcessException(String errorCode, String message)
    {
        super(errorCode);
        this.errorCode =errorCode;
        this.message=message;
    }
}
