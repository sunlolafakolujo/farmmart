package com.farmmart.data.exception;

public class ProvinceException extends Exception{
    public ProvinceException() {
        super();
    }

    public ProvinceException(String message) {
        super(message);
    }

    public ProvinceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProvinceException(Throwable cause) {
        super(cause);
    }

    protected ProvinceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
