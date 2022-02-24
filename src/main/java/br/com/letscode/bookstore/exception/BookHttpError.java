package br.com.letscode.bookstore.exception;

public class BookHttpError {

    private String errorMessage;
    private String httpErroName;
    private int httpErrorCode;

    public BookHttpError(String errorMessage, String httpErroName, int httpErrorCode) {
        this.errorMessage = errorMessage;
        this.httpErroName = httpErroName;
        this.httpErrorCode = httpErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getHttpErroName() {
        return httpErroName;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }
}
