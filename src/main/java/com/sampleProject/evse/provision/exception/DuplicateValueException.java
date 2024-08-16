package com.sampleProject.evse.provision.exception;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DuplicateValueException extends Exception{

    private int errorCode = 1000;


    public DuplicateValueException(String message){
        super(message);
    }
}
