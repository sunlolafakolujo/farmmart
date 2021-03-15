package com.farmmart.data.exception;

public class LocalGovernmentOrCountyException extends Exception{

    public LocalGovernmentOrCountyException() {
        super();
    }

    public LocalGovernmentOrCountyException(String message) {
        super(message);
    }

    public LocalGovernmentOrCountyException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalGovernmentOrCountyException(Throwable cause) {
        super(cause);
    }

    protected LocalGovernmentOrCountyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
