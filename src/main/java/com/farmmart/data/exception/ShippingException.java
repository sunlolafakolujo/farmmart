package com.farmmart.data.exception;

public class ShippingException extends Exception{
    public ShippingException() {
        super();
    }

    public ShippingException(String message) {
        super(message);
    }

    public ShippingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShippingException(Throwable cause) {
        super(cause);
    }

    protected ShippingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
