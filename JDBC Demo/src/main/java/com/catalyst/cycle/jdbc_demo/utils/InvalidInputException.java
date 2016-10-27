package com.catalyst.cycle.jdbc_demo.utils;

import java.util.List;

public class InvalidInputException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private List<String> errorCodes;
     
    
    public InvalidInputException(String message, List<String> errorCodes){
        super(message);
        this.errorCodes = errorCodes; 
    }
    
    public InvalidInputException(List<String> errorCodes){
        this.errorCodes = errorCodes; 
    }
    
    public List<String> getErrorCodes(){
        return this.errorCodes;
    }
    
    
    

}
