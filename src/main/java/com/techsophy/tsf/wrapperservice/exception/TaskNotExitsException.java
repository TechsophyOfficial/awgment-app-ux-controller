package com.techsophy.tsf.wrapperservice.exception;

public class TaskNotExitsException  extends RuntimeException{
    String errorCode;
    String message;
    public TaskNotExitsException(String errorCode, String message)
    {
        super(errorCode);
        this.errorCode =errorCode;
        this.message=message;
    }
}
