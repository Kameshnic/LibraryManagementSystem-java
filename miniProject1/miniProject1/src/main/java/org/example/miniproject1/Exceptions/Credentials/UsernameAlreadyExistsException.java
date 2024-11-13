package org.example.miniproject1.Exceptions.Credentials;

public class UsernameAlreadyExistsException extends ValidationException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}