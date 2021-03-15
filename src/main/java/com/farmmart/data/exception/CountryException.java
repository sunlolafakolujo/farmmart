package com.farmmart.data.exception;

public class CountryException extends Exception{

    public CountryException() {
        super();
    }

    public CountryException(String message) {
        super(message);
    }

    public CountryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryException(Throwable cause) {
        super(cause);
    }

    protected CountryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
