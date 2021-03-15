package com.farmmart.data.model;

public enum PaymentMethod {

    AMERICAN_EXPRESS,
    MASTER_CARD,
    VERVE,
    VISA_CARD,
    PAYPAL,
    BANK_TRANSFER,
    CHEQUE;

    String paymentMethod="";

    @Override
    public String toString() {

        switch (this){
            case PAYPAL:
                paymentMethod="Paypal";
                break;
            case BANK_TRANSFER:
                paymentMethod="Bank Transfer";
                break;
            case CHEQUE:
                paymentMethod="Cheque";
                break;
            case VERVE:
                paymentMethod="Verve";
                break;
            case AMERICAN_EXPRESS:
                paymentMethod="America Express";
                break;
            case VISA_CARD:
                paymentMethod="Visa Card";
                break;
            case MASTER_CARD:
                paymentMethod="Master Card";
                break;
        }
        return paymentMethod;
    }
}
