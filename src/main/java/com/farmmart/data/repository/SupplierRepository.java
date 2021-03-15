package com.farmmart.data.repository;

import com.farmmart.data.exception.SupplierException;
import com.farmmart.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    public default Supplier saveSupplier(Supplier supplier) throws SupplierException {
        if(!isSupplierAddressValid(supplier)){
            throw new SupplierException("Supplier Address cannot be null");
        }
        if(!isSupplierCategoryValid(supplier)){
            throw new SupplierException("Supplier Category cannot be null");
        }
        if(!isSupplierFacilityTypeValid(supplier)){
            throw new SupplierException("Supplier Facility type cannot be null");
        }
        if(!invalidPhoneNumber(supplier)){
            throw new SupplierException("Invalid Supplier phone number");
        }
        supplier.setPassword(encryptPassword(supplier.getPassword()));

        return save(supplier);
    }

    private boolean isSupplierAddressValid(Supplier supplier){
        if(supplier.getAddresses()==null) {
            return false;
        }
        return true;
    }

    private boolean isSupplierCategoryValid(Supplier supplier){
        if(supplier.getSupplierCategory()==null){
            return false;
        }
        return true;
    }

    private boolean isSupplierFacilityTypeValid(Supplier supplier){
        if(supplier.getFacility()==null){
            return false;
        }
        return true;
    }

    private boolean invalidPhoneNumber(Supplier supplier){
        if(supplier.getPhoneNumber().matches("\\d{10}"))
            return true;
        else if(supplier.getPhoneNumber().matches("\\d{11}"))
            return true;
        else if(supplier.getPhoneNumber().matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        else if(supplier.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        else if(supplier.getPhoneNumber().matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
        else
            return false;
    }


    private String encryptPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}
