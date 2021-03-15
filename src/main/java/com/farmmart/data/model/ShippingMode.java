package com.farmmart.data.model;

public enum ShippingMode {
    COURIER,
    TRUCK;

    String shippingMode="";

    @Override
    public String toString() {
        switch (this){
            case COURIER:
                shippingMode="Courier";
                break;
            case TRUCK:
                shippingMode="Truck";
        }
        return shippingMode;
    }
}
