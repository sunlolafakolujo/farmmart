package com.farmmart.data.model;


public enum SupplierCategory {

    PRODUCT_SUPPLIER,
    SERVICE_PROVIDER,
    PRODUCT_AND_SERVICE_PROVIDER;

    String supplierType="";

    @Override
    public String toString() {

        switch (this){
            case SERVICE_PROVIDER:
                supplierType="Service Provider";
                break;
            case PRODUCT_SUPPLIER:
                supplierType="Product Supplier";
                break;
            case PRODUCT_AND_SERVICE_PROVIDER:
                supplierType="Product and Service Provider";
                break;
        }
        return supplierType;
    }
}
