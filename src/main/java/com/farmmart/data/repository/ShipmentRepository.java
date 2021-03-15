package com.farmmart.data.repository;

import com.farmmart.data.exception.ShippingException;
import com.farmmart.data.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

    public default Shipment saveShipment(Shipment shipment) throws ShippingException {
        if(!isShippingModeValid(shipment)){
            throw new ShippingException("Shipping mode cannot be null");
        }

        return save(shipment);
    }

    private boolean isShippingModeValid(Shipment shipment){
        if(shipment.getShippingMode()==null){
            return false;
        }
        return true;
    }

}
