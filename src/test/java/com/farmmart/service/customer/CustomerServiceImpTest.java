package com.farmmart.service.customer;

import com.farmmart.data.exception.CustomerException;
import com.farmmart.data.model.Customer;
import com.farmmart.data.repository.CustomerRepository;
import com.farmmart.service.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class CustomerServiceImpTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService = new CustomerServiceImp();

    Customer customer;

    CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer=new Customer();

        customerDTO =new CustomerDTO();
    }

    @Test
    void mockAddCustomerTest() throws CustomerException {
        Mockito.when(customerRepository.saveCustomer(customer)).thenReturn(customer);

        customerService.addCustomer(customer);

        Mockito.verify(customerRepository,Mockito.times(1)).saveCustomer(customer);
    }

    @Test
    void mockFindCustomerDTOByIdTest() throws CustomerException {

        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        customerService.findCustomerById(1);

        Mockito.verify(customerRepository,Mockito.times(1)).findById(1);
    }

    @Test
    void mockFindAllCustomerTest(){
        List<Customer> customers =new ArrayList<>();
        Mockito.when(customerRepository.findAll()).thenReturn((List<Customer>) customer);

        customerService.findAllCustomers();

        Mockito.verify(customerRepository,Mockito.times(1)).findAll();
    }

    @Test
    void mockUpdateCustomerTest() throws CustomerException {
        Mockito.when(customerRepository.saveCustomer(customer)).thenReturn(customer);

        customerService.updateCustomer(1,customer);

        Mockito.verify(customerRepository,Mockito.times(1)).saveCustomer(customer);
    }

    @Test
    void mockDeleteCustomerByIdTest(){
        Mockito.doNothing().when(customerRepository).deleteById(1);

        customerService.deleteCustomerById(1);

        Mockito.verify(customerRepository,Mockito.times(1)).deleteById(1);
    }

    @Test
    void mockDeleteAllCustomersTest(){
        Mockito.doNothing().when(customerRepository).deleteAll();

        customerService.deleteAllCustomers();

        Mockito.verify(customerRepository,Mockito.times(1)).deleteAll();

    }


}