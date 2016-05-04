/**
 * @file
 * This file contains implementation of
 * com.irurueta.server.commons.CommonsException
 * 
 * @author Alberto Irurueta (alberto@irurueta.com)
 * @date May 25, 2013
 */
package com.irurueta.server.commons;

/**
 * Base exception class for this package.
 * All exceptions in this package extends from this one.
 */
public class CommonsException extends Exception{
    /**
     * Constructor
     */
    public CommonsException(){
        super();
    }

    /**
     * Constructor with String containing message
     * @param message Message indicating the cause of the exception
     */
    public CommonsException(String message){
        super(message);
    }

    /**
     * Constructor with message and cause
     * @param message Message describing the cause of the exception
     * @param cause Instance containing the cause of the exception
     */
    public CommonsException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * Constructor with cause
     * @param cause Instance containing the cause of the exception
     */
    public CommonsException(Throwable cause){
        super(cause);
    }                    
}
