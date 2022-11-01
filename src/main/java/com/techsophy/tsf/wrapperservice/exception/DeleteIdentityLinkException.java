package com.techsophy.tsf.wrapperservice.exception;

public class DeleteIdentityLinkException extends RuntimeException {
    final String errorcode;
    final String message;
    public DeleteIdentityLinkException(String errorCode, String message)
    {
        super(errorCode);
        this.errorcode=errorCode;
        this.message=message;
    }
}

