package com.farmmart.data.exception;

public class SupplierException extends Exception{

    public SupplierException() {
        super();
    }

    public SupplierException(String message) {
        super(message);
    }

    public SupplierException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupplierException(Throwable cause) {
        super(cause);
    }

    protected SupplierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
