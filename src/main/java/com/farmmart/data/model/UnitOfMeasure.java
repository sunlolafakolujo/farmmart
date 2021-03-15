package com.farmmart.data.model;

public enum UnitOfMeasure {
    KG,
    LTR,
    BAG,
    FT,
    PACK,
    PIECE;

    String unitOfMeasure="";

    @Override
    public String toString() {

        switch (this){
            case BAG:
                unitOfMeasure="Bag";
                break;
            case KG:
                unitOfMeasure="Kilogram";
                break;
            case FT:
                unitOfMeasure="Feet";
                break;
            case LTR:
                unitOfMeasure="Litre";
                break;
            case PACK:
                unitOfMeasure="Pack";
                break;
            case PIECE:
                unitOfMeasure="Piece";
                break;
        }
        return unitOfMeasure;
    }
}
