package com.farmmart.data.repository;

import com.farmmart.data.model.Customer;
import com.farmmart.data.model.CustomerOrder;
import com.farmmart.data.model.Shipment;
import com.farmmart.data.model.ShippingMode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class CustomerOrderRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @Autowired
    CustomerOrderRepository customerOrderRepository;
    CustomerOrder customerOrder;

    @Autowired
    ShipmentRepository shipmentRepository;
    Shipment shipment;

    @Autowired
    OrderItemRepository orderItemRepository;

    @BeforeEach
    void setUp() {

        customer =new Customer();
        customerOrder = new CustomerOrder();
        shipment=new Shipment();
    }

    @Test
    void testThatYouCanSaveCustomerOrder() throws ParseException {
        customer = customerRepository.findById(1).orElse(null);

        shipment.setShippingCharges(BigDecimal.valueOf(35000));
        shipment.setShippingMode(ShippingMode.TRUCK);//test to fail

        customerOrder.setCustomer(customer); //Test to fail
        customerOrder.setShippingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-22"));
        customerOrder.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-29"));
        customerOrder.setShipment(shipment);//Test to fail

        log.info("Customer Order before saving -->{}",customerOrder);

        assertDoesNotThrow(()->shipmentRepository.saveShipment(shipment));
        assertDoesNotThrow(()-> customerOrderRepository.saveCustomerOrder(customerOrder));

        log.info("Customer Order after saving -->{}",customerOrder);


    }

    @Test
    void testThatYouCanFindShipmentById(){
        customerOrder =customerOrderRepository.findById(1).orElse(null);

        assertNotNull(customerOrder);

        log.info("Customer Order with ID 1 is-->{}",customerOrder);
    }

    @Test
    void testThatYouCanFindAllCustomerOrder(){

        List<CustomerOrder> customerOrderList =customerOrderRepository.findAll();

        customerOrderList.stream().forEach(System.out::println);
    }

    @Test
    void testThatYouCanUpdateCustomerOrderById() throws ParseException {
        customerOrder=customerOrderRepository.findById(1).orElse(null);

        customerOrder.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-02"));

        assertThat(customerOrder.getDeliveryDate()).isEqualTo("2021-02-02");

        log.info("Customer Order ID 1 Updated record is-->{}",customerOrder);
    }

    @Test
    void testThatYouCanDeleteCustomerOrderById(){

        shipmentRepository.deleteById(1);
        orderItemRepository.deleteById(1);
        orderItemRepository.deleteById(2);
        orderItemRepository.deleteById(3);
        customerOrderRepository.deleteById(1);
        customerRepository.deleteById(1);

        assertThat(customerOrder.getId()).isNull();
        log.info("Customer order with ID 1-->{}",customerOrder.getId());

    }

    @Test
    void testThatYouCanDeleteAllCustomerOrder(){
        shipmentRepository.deleteAll();
        orderItemRepository.deleteAll();
        customerOrderRepository.deleteAll();
        customerRepository.deleteAll();

    }

}