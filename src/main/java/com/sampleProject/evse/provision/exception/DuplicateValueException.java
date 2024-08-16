package com.sampleProject.evse.provision.exception;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DuplicateValueException extends Exception{

    private int errorCode = 1000;
    private String message;

    public DuplicateValueException(String message){
        this.message = message;
    }
}
