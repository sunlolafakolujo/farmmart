package com.farmmart.data.model;

public enum ProductCategory {

    HORTICULTURE,
    POULTRY,
    AQUATIC,
    LIVESTOCK,
    FRUIT_CROPS,
    CASH_CROPS,
    CEREAL_CROPS,
    VEGETABLE_CROPS,
    TUBER_CROPS,
    TIMBER,
    EQUIPMENT,
    ACCESSORIES,
    OTHERS;

    String productCategory="";

    @Override
    public String toString() {

        String productCategory ="";

        switch(this) {
            case FRUIT_CROPS:
                productCategory = "Fruit Crops";
                break;
            case CASH_CROPS:
                productCategory = "Cash Crops";
                break;
            case CEREAL_CROPS:
                productCategory = "Cereal Crops";
                break;
            case VEGETABLE_CROPS:
                productCategory = "Vegetable Crops";
                break;
            case TUBER_CROPS:
                productCategory = "Tuber Crops";
                break;
            case TIMBER:
                productCategory = "Timber";
                break;
            case AQUATIC:
                productCategory = "Aquatic";
                break;
            case POULTRY:
                productCategory = "Poultry";
                break;
            case EQUIPMENT:
                productCategory = "Equipment";
                break;
            case OTHERS:
                productCategory = "Others";
                break;
            case LIVESTOCK:
                productCategory = "Livestock";
                break;
            case ACCESSORIES:
                productCategory = "Accessories";
                break;
            case HORTICULTURE:
                productCategory = "Horticulture";
                break;
        }

            return productCategory;
    }
}
