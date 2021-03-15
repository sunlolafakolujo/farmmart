package com.farmmart.data.exception;

public class BillingDetailsException extends Exception{

    public BillingDetailsException() {
        super();
    }

    public BillingDetailsException(String message) {
        super(message);
    }

    public BillingDetailsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BillingDetailsException(Throwable cause) {
        super(cause);
    }

    protected BillingDetailsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
