/**
 * 
 */
package com.catalyst.cycle.jdbc_demo.utils;

/**
 * An example of a checked exception that is specific to our application. Any
 * methods that throw this exception have to declare it in the method signature.
 * Any use of such methods without catching the exception requires the method
 * they are contained within to add the throws statement to their definition.
 * This allows us to further expand our API to handle user errors without the
 * need of a Data Transfer Object (DTO). Avoid heavy use of exceptions in cases
 * where errors can be corrected.
 * 
 * This class will be used when the database cannot retrieve an entry with a provided
 * key.
 * 
 * @author egover
 *
 */
public class InvalidIdentifierException extends Exception {
    private static final long serialVersionUID = 1L;

    private Object key;
    
    
    /**
     * 
     * @param message The message with a description of what the exception is. 
     * @param key The key responsible for the invalid entry.
     */
    public InvalidIdentifierException(String message, Object key) {
        super(message);
        this.key = key;
    }

  
    /**
     * 
     * @param message The message with a description of what the exception is.
     * @param cause The cause of the exception.
     * @param key The key responsible for the invalid entry.
     */
    public InvalidIdentifierException(String message, Throwable cause, Object key) {
        super(message, cause);
        this.key=key;
    }
    
    
    /**
     * 
     * @return The key responsible for the invalid entry.
     */
    public Object getKey(){
        return this.key;
    }

}
