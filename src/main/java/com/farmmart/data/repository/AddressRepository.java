package com.farmmart.data.repository;

import com.farmmart.data.exception.AddressException;
import com.farmmart.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {


    public default Address saveAddress(Address address) throws AddressException {
        if(!isAddressValid(address)){
            throw new AddressException("Local Government or County cannot be null");
        }

        if(!addressType(address)){
            throw new AddressException("Address Type cannot be null");
        }
        return save(address);
    }

    private boolean isAddressValid(Address address){
        if(address.getLocalGovernmentOrCounty() == null){
            return false;
        }else
            return true;
    }

    private  boolean addressType(Address address){
        if(address.getAddressType() == null){
            return false;
        }
        return true;
    }
}
