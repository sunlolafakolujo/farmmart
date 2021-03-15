package com.farmmart.data.model;

public enum FacilityType {
    OWN,
    LEASE;

    String facility="";

    @Override
    public String toString() {

        switch (this){
            case LEASE:
                facility="Own";
                break;
            case OWN:
                facility="Lease";
                break;
        }
        return facility;
    }
}
