package org.example.miniproject1.Exceptions.Credentials;

public class InvalidMobileNumberException extends ValidationException {
    public InvalidMobileNumberException(String message) {
        super(message);
    }
}