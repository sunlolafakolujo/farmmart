package com.farmmart.service.Address;

import com.farmmart.data.exception.AddressException;
import com.farmmart.data.model.Address;
import com.farmmart.data.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressService addressService=new AddressServiceImpl();

    Address address;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address =new Address();
    }

    @Test
    void mockSaveAddressTest() throws AddressException {

        when(addressRepository.saveAddress(address)).thenReturn(address);
        addressService.saveAddress(address);

        verify(addressRepository,times(1)).saveAddress(address);
    }

    @Test
    void mockFindAddressByIdTest() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        addressService.findAddressById(1);

        verify(addressRepository,times(1)).findById(1);
    }

    @Test
    void mockFindAllAddressTest(){
        List<Address> addresses = new ArrayList<>();

        when(addressRepository.findAll()).thenReturn(addresses);
        addressService.findAllAddresses();

        verify(addressRepository,times(1)).findAll();
    }

    @Test
    void mockUpdateAddressTest(){

    }

    @Test
    void mockDeleteAddressByIdTest(){

        doNothing().when(addressRepository).deleteById(1);

        addressService.deleteAddressById(1);

        verify(addressRepository,times(1)).deleteById(1);
    }

    @Test
    void mockDeleteAllAddressesTest(){
        doNothing().when(addressRepository).deleteAll();

        addressService.deleteAllAddresses();

        verify(addressRepository,times(1)).deleteAll();
    }
}