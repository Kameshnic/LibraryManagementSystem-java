package org.example.miniproject1.Exceptions.AuthorException;

public class BookPublishingException extends RuntimeException {
    public BookPublishingException(String message) {
        super(message);
    }
}
