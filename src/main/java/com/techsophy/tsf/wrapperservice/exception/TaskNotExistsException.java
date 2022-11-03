package com.techsophy.tsf.wrapperservice.exception;

public class TaskNotExistsException extends RuntimeException{
    String errorCode;
    String message;
    public TaskNotExistsException(String errorCode, String message)
    {
        super(errorCode);
        this.errorCode =errorCode;
        this.message=message;
    }
}
