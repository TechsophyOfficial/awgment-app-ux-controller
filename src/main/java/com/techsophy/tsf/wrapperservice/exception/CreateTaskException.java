package com.techsophy.tsf.wrapperservice.exception;

public class CreateTaskException extends RuntimeException{
    String errorcode;
    String message;
    public CreateTaskException(String errorCode, String message)
    {
        super(errorCode);
        this.errorcode=errorCode;
        this.message=message;
    }
}
