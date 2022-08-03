package com.techsophy.tsf.wrapperservice.exception;

public class UpdateTaskException extends RuntimeException{
    String errorCode;
    String message;
    public UpdateTaskException(String errorCode, String message)
    {
        super(errorCode);
        this.errorCode =errorCode;
        this.message=message;
    }
}
