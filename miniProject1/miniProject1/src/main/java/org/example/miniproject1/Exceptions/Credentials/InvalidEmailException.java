package org.example.miniproject1.Exceptions.Credentials;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
