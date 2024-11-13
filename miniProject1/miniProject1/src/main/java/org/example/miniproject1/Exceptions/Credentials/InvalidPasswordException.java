package org.example.miniproject1.Exceptions.Credentials;

public class InvalidPasswordException extends ValidationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}