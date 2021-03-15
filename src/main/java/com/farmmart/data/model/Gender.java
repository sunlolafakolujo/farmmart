package com.farmmart.data.model;

public enum Gender {
    MALE,
    FEMALE;

    String gender ="";

    @Override
    public String toString() {

        switch (this){
            case FEMALE:
                gender="Female";
                break;
            case MALE:
                gender="Male";
                break;
        }
        return gender;
    }
}
