package com.farmmart.data.repository;

import com.farmmart.data.model.Shipment;
import com.farmmart.data.model.ShippingMode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class ShipmentRepositoryTest {

    @Autowired
    ShipmentRepository shipmentRepository;
    Shipment shipment;

    @BeforeEach
    void setUp() {
        shipment=new Shipment();
    }

    @Test
    void testThatYouCanSaveShipment(){
        shipment.setShippingCharges(BigDecimal.valueOf(10000));
        shipment.setShippingMode(ShippingMode.COURIER);

        log.info("Shipment repo before saving--{}",shipment);

        assertDoesNotThrow(()->shipmentRepository.saveShipment(shipment));

        log.info("Shipment repo after saving--{}",shipment);
    }

    @Test
    void testYouCanFindShipmentById(){
        shipment=shipmentRepository.findById(1).orElse(null);

        assertNotNull(shipment);

        log.info("Shipment with is 1 is-->{}",shipment);
    }

    @Test
    void testThatYouCanFindAllShipment(){

        List<Shipment> shipmentList = shipmentRepository.findAll();

        shipmentList.stream().forEach(System.out::println);
        //log.info("List of Shipment -->{}",shipmentList);
    }

    @Test
    void testThatYouCanUpdateCustomerShipment(){
        shipment=shipmentRepository.findById(1).orElse(null);
        shipment.setShippingMode(ShippingMode.COURIER);
        shipment.setShippingCharges(BigDecimal.valueOf(50000));

        assertDoesNotThrow(() ->shipmentRepository.saveShipment(shipment));

        assertThat(shipment.getShippingMode()).isEqualTo(ShippingMode.COURIER);
        assertThat(shipment.getShippingCharges()).isEqualTo(BigDecimal.valueOf(50000));

        log.info("Updated shipment with ID 1 -->{}",shipment);

    }

    @Test
    void testThatYouCanDeleteShipmentById(){

        shipmentRepository.deleteById(1);

        assertThat(shipment.getId()).isNull();

        log.info("Shipment with ID 1-->{}",shipment.getId());

    }

    @Test
    void testThatYouCanDeleteAllShipments(){

        shipmentRepository.deleteAll();

        assertThat(shipment.getId()).isNull();

        log.info("Shipment with ID 1-->{}",shipment.getId());
        
    }

}