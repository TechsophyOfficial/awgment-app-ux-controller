package com.techsophy.tsf.wrapperservice.exception;

import lombok.Getter;

@Getter
public class ServerErrorException extends RuntimeException{
    private final String[] args;
    public ServerErrorException(String errorCode, String... args)
    {
        super(errorCode);
        this.args = args;
    }


}
