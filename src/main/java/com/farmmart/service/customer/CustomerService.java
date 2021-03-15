package com.farmmart.service.customer;

import com.farmmart.data.exception.CustomerException;
import com.farmmart.data.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer) throws CustomerException;
    Customer findCustomerById(Integer id) throws CustomerException;
    List<Customer> findAllCustomers();
    Customer updateCustomer(Integer id,Customer customer) throws CustomerException;
    void deleteCustomerById(Integer id);
    void deleteAllCustomers();
}
