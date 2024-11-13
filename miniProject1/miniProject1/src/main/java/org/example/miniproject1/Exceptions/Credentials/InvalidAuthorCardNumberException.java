package org.example.miniproject1.Exceptions.Credentials;

// InvalidAuthorCardNumberException.java
public class InvalidAuthorCardNumberException extends Exception {
    
    // Constructor that accepts a custom error message
    public InvalidAuthorCardNumberException(String message) {
        super(message);
    }
}