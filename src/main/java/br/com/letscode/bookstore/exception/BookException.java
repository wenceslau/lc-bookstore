package br.com.letscode.bookstore.exception;

public class BookException extends RuntimeException {

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookException(String message) {
        super(message);
    }
}
