package com.yassine7h.parcauto.exceptions;

public class ResourceExistException extends RuntimeException{
    private Object id;
    private Class resourceClass;
    public ResourceExistException(Object id,Class resourceClass){
        super("Resource of type "+resourceClass.getName()+" with id '"+id.toString()+"' already exist");
    }
}
