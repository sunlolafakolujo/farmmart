package com.farmmart.data.model;

public enum OrderStatus {

    CANCELLED,
    DELIVERED,
    RETURN,
    SHIPPED,
    ORDERED,
    DAMAGED,
    PREPARING_FOR_SHIPMENT,
    IN_TRANSIT;

    String orderStatus="";

    @Override
    public String toString() {

        switch (this){
            case DELIVERED:
                orderStatus="Delivered";
                break;
            case CANCELLED:
                orderStatus="Cancelled";
                break;
            case RETURN:
                orderStatus="Return";
                break;
            case IN_TRANSIT:
                orderStatus="In Transit";
                break;
            case SHIPPED:
                orderStatus="Shipped";
            case ORDERED:
                orderStatus="Ordered";
                break;
            case PREPARING_FOR_SHIPMENT:
                orderStatus="Preparing for shipment";
            case DAMAGED:
                orderStatus="Damaged";
        }
        return orderStatus;
    }
}
