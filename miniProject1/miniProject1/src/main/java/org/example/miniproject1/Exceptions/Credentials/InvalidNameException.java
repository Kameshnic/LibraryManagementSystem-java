package org.example.miniproject1.Exceptions.Credentials;

public class InvalidNameException extends ValidationException {
    public InvalidNameException(String message) {
        super(message);
    }
}