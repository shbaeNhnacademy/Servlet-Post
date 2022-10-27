package com.nhnacademy.exception;

public class SameUserIdException extends RuntimeException{

    public SameUserIdException(String msg) {
        super(msg);
    }
}
