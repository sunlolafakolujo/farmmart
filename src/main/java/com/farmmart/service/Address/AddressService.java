package com.farmmart.service.Address;

import com.farmmart.data.exception.AddressException;
import com.farmmart.data.model.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address) throws AddressException;
    Address findAddressById(Integer id);
    List<Address> findAllAddresses();
    void updateAddress(Integer id,Address address);
    void deleteAddressById(Integer id);
    void deleteAllAddresses();
}
