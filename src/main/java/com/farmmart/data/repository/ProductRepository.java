package com.farmmart.data.repository;

import com.farmmart.data.exception.ProductException;
import com.farmmart.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    public default Product saveProduct(Product product) throws ProductException {
        if(!isValidProductCategory(product)){
            throw new ProductException("Product Category cannot be null");
        }
        if(!isValidProductUnitOfMeasure(product)){
            throw new ProductException("Product Unit of Measure cannot be null");
        }
        if(!isValidProductSupplier(product)){
            throw new ProductException("Product Supplier cannot be null");
        }

        return save(product);
    }

    private boolean isValidProductCategory(Product product){
        if(product.getProductCategory()==null){
            return false;
        }
        return true;
    }

    private boolean isValidProductUnitOfMeasure(Product product){
        if(product.getProductUnitOfMeasure()==null){
            return false;
        }
        return true;
    }

    private boolean isValidProductSupplier(Product product){
        if(product.getSuppliers()==null){
            return false;
        }
        return true;
    }
}
