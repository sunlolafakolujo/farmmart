package com.farmmart.service.Address;

import com.farmmart.data.exception.AddressException;
import com.farmmart.data.model.Address;
import com.farmmart.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;



    @Override
    public Address saveAddress(Address address) throws AddressException {
        return addressRepository.saveAddress(address);
    }

    @Override
    public Address findAddressById(Integer id) {

        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findAllAddresses() {
        List<Address> addresses = new ArrayList<>();

        addressRepository.findAll().forEach(addresses::add);

        return addresses;
    }

    @Override
    public void updateAddress(Integer id, Address address) {

    }

    @Override
    public void deleteAddressById(Integer id) {
        addressRepository.deleteById(id);

    }

    @Override
    public void deleteAllAddresses() {
        addressRepository.deleteAll();

    }
}
