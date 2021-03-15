package com.farmmart.data.repository;

import com.farmmart.data.exception.CustomerException;
import com.farmmart.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public default Customer saveCustomer(Customer customer) throws CustomerException {
        if(!isCustomerAddressValid(customer)){
            throw new CustomerException("Customer Address cannot be null");
        }

        if(!invalidPhoneNumber(customer)){
            throw new CustomerException("Invalid Phone Number");
        }

        if(!isCustomerBillingDetailsValid(customer)){
            throw new CustomerException("Customer billing details cannot be null");
        }


        return save(customer);
    }

    private boolean isCustomerAddressValid(Customer customer){

        if(customer.getAddresses()==null){
            return false;
        }
            return true;
    }

    private boolean isCustomerBillingDetailsValid(Customer customer){
        if(customer.getBillingDetails() == null){
            return false;
        }
        return true;
    }

    private boolean invalidPhoneNumber(Customer customer){
        if(customer.getPhoneNumber().matches("\\d{10}"))
            return true;
        else if(customer.getPhoneNumber().matches("\\d{11}"))
            return true;
        else if(customer.getPhoneNumber().matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        else if(customer.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        else if(customer.getPhoneNumber().matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
        else
            return false;
    }

}
