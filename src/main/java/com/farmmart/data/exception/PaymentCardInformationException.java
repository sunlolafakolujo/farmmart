package com.farmmart.data.exception;

public class PaymentCardInformationException extends Exception{

    public PaymentCardInformationException() {
        super();
    }

    public PaymentCardInformationException(String message) {
        super(message);
    }

    public PaymentCardInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentCardInformationException(Throwable cause) {
        super(cause);
    }

    protected PaymentCardInformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
