package com.techsophy.tsf.wrapperservice.exception;

public class AddUserOrGroupToTaskException extends RuntimeException {
    final String errorcode;
    final String message;
    public  AddUserOrGroupToTaskException(String errorCode, String message)
    {
        super(errorCode);
        this.errorcode=errorCode;
        this.message=message;
    }
}
