package com.farmmart.data.repository;

import com.farmmart.data.exception.OrderItemException;
import com.farmmart.data.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    public default OrderItem saveOrderItem(OrderItem orderItem) throws OrderItemException {

        if(!isCustomerOrderValid(orderItem)){
            throw new OrderItemException("Customer order cannot be null");
        }

        if(!isOrderProductValid(orderItem)){
            throw new OrderItemException("Product cannot be null");
        }

        return save(orderItem);
    }

    private boolean isCustomerOrderValid(OrderItem orderItem){
        if(orderItem.getCustomerOrder()==null){
            return false;
        }
        return true;
    }

    private boolean isOrderProductValid(OrderItem orderItem){
        if(orderItem.getProducts()==null){
            return false;
        }
        return true;
    }
}
