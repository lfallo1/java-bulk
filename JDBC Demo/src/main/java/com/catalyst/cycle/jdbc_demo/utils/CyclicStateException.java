package com.catalyst.cycle.jdbc_demo.utils;

import com.catalyst.cycle.jdbc_demo.model.User;

/**
 * Thrown when the state of an object has a detectable self reference.
 * @author egover
 *
 */
public class CyclicStateException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private User user;
 
    public CyclicStateException(String message, Throwable cause, User user){
        super(message,cause);
        this.user=user;
    }
    
    public CyclicStateException(String message, User user){
        super(message);
        this.user=user;
    }
    
    public User getUser(){
        return this.user;
    }
}
