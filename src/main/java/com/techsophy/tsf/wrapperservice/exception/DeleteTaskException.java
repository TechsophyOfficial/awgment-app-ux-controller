package com.techsophy.tsf.wrapperservice.exception;

public class DeleteTaskException extends  RuntimeException
{
    String errorcode;
    String message;
    public DeleteTaskException(String errorCode, String message)
    {
        super(errorCode);
        this.errorcode=errorCode;
        this.message=message;
    }
}
