package org.example.miniproject1.Exceptions.AuthorException;

public class FileNotExistException extends Exception {
    public FileNotExistException(String message) {
        super(message);
    }
}