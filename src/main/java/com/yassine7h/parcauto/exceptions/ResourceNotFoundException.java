package com.yassine7h.parcauto.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private Object id;
    private Class resourceClass;
    public ResourceNotFoundException(Object id,Class resourceClass){
        super("Resource of type "+resourceClass.getName()+" with id '"+id.toString()+"' not found");
    }
}
