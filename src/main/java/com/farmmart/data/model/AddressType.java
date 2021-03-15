package com.farmmart.data.model;

public enum AddressType {
    BILLING,
    DELIVERY,
    POSTAL,
    RESIDENTIAL,
    OFFICE;

    String addressType="";

    @Override
    public String toString() {

        switch (this){
            case DELIVERY:
                addressType="Delivery";
                break;
            case BILLING:
                addressType="Billing";
                break;
            case POSTAL:
                addressType="Postal";
                break;
            case OFFICE:
                addressType="Office";
                break;
            case RESIDENTIAL:
                addressType="Residential";
                break;
        }
        return addressType;
    }
}
