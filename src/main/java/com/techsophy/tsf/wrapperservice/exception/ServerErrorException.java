package com.techsophy.tsf.wrapperservice.exception;

public class ServerErrorException extends RuntimeException{
    private final String[] args;
    public ServerErrorException(String errorCode, String... args)
    {
        super(errorCode);
        this.args = args;
    }
}
