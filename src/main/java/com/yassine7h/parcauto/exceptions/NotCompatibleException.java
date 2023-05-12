package com.yassine7h.parcauto.exceptions;

public class NotCompatibleException extends RuntimeException{
    private Object id;
    private Class resourceClass;
    public NotCompatibleException(String msg){
        super(msg);
    }
}
