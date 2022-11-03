package com.techsophy.tsf.wrapperservice.exception;

public class FailedTaskDeletionException extends RuntimeException
{
    String errorCode;
    String message;
    public FailedTaskDeletionException(String errorCode, String message)
    {
        super(errorCode);
        this.errorCode =errorCode;
        this.message=message;
    }
}
