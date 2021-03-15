package com.farmmart.data.repository;

import com.farmmart.data.exception.CustomerOrderException;
import com.farmmart.data.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Integer> {

    public default CustomerOrder saveCustomerOrder(CustomerOrder customerOrder) throws CustomerOrderException {
        if(!isCustomerOrderValid(customerOrder)){
            throw new CustomerOrderException("Customer cannot be null");
        }

        if(!isCustomerShipmentValid(customerOrder)){
            throw  new CustomerOrderException("Shipping cannot be null");
        }

        return save(customerOrder);
    }

    private boolean isCustomerOrderValid(CustomerOrder customerOrder){
        if(customerOrder.getCustomer() ==null){
            return false;
        }
        return true;
    }

    private boolean isCustomerShipmentValid(CustomerOrder customerOrder){
        if(customerOrder.getShipments()==null){
            return false;
        }
        return true;
    }
}
