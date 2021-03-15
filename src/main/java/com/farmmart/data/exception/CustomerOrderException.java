package com.farmmart.data.exception;

public class CustomerOrderException extends Exception{
    public CustomerOrderException() {
        super();
    }

    public CustomerOrderException(String message) {
        super(message);
    }

    public CustomerOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerOrderException(Throwable cause) {
        super(cause);
    }

    protected CustomerOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
