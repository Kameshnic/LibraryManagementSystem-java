package org.example.miniproject1.Exceptions.Credentials;

public class InvalidStudentCardNumberException extends ValidationException {
    public InvalidStudentCardNumberException(String message) {
        super(message);
    }
}