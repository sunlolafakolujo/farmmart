package com.farmmart.data.repository;

import com.farmmart.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class OrderItemRepositoryTest {

    @Autowired
    OrderItemRepository orderItemRepository;
    OrderItem orderItem;

    @Autowired
    CustomerOrderRepository customerOrderRepository;
    CustomerOrder customerOrder;

    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @Autowired
    ProductRepository productRepository;
    Product product;

    @Autowired
    ShipmentRepository shipmentRepository;
    Shipment shipment;

    @Autowired
    SupplierRepository supplierRepository;
    Supplier supplier;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
        customerOrder=new CustomerOrder();
        product=new Product();
        shipment=new Shipment();
        customer =new Customer();
        supplier = new Supplier();
    }

    @Test
    void testThatYouCanSaveOrderItem() throws ParseException {

        customer=customerRepository.findById(1).orElse(null);

        shipment.setShippingMode(ShippingMode.TRUCK);
        shipment.setShippingCharges(BigDecimal.valueOf(35000));

        customerOrder.setCustomer(customer);
        customerOrder.setShipment(shipment);
        customerOrder.setShippingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-22"));
        customerOrder.setDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-29"));

        supplier=supplierRepository.findById(1).orElse(null);

        product.setProductPrice(BigDecimal.valueOf(10000));
        product.setProductSize(ProductSize.MEDIUM);
        product.setProductUnitOfMeasure(UnitOfMeasure.BAG);
        product.setProductCategory(ProductCategory.VEGETABLE_CROPS);
        product.setProductName("Bell Pepper");
        product.setProductDescription("It belongs to the family of Grossum cultivar of the species Capsicum annuum");
        product.setProductColour("Red");
        product.setSupplier(supplier);
        product.setProductCondition(ProductCondition.FRESH);

        Product product1 = new Product();
        product1.setProductPrice(BigDecimal.valueOf(12000));
        product1.setProductSize(ProductSize.SMALL);
        product1.setProductUnitOfMeasure(UnitOfMeasure.BAG);
        product1.setProductCategory(ProductCategory.VEGETABLE_CROPS);
        product1.setProductName("Habanero");
        product1.setProductDescription("Is a hot variety of the chilli pepper.");
        product1.setProductColour("Red");
        product1.setSupplier(supplier);
        product1.setProductCondition(ProductCondition.FRESH);

        orderItem.setActualDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"));
        orderItem.setValueAddedTax(BigDecimal.valueOf(0.07));
        orderItem.setCustomerOrder(customerOrder);
        orderItem.setOrderQuantity(BigDecimal.valueOf(100));
        orderItem.setOrderStatus(OrderStatus.ORDERED);
        orderItem.setProduct(product);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setActualDeliveryDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"));
        orderItem1.setValueAddedTax(BigDecimal.valueOf(0.07));
        orderItem1.setCustomerOrder(customerOrder);
        orderItem1.setOrderQuantity(BigDecimal.valueOf(100));
        orderItem1.setOrderStatus(OrderStatus.ORDERED);
        orderItem1.setProduct(product1);

        List<OrderItem> orderItemList=new ArrayList<>();
        orderItemList.add(orderItem);
        orderItemList.add(orderItem1);

        log.info("Ordered Items before saving -->{}",orderItemList);
        //log.info("Ordered Items before saving -->{}",orderItem1);

        assertDoesNotThrow(()->shipmentRepository.saveShipment(shipment));
        assertDoesNotThrow(()->customerOrderRepository.saveCustomerOrder(customerOrder));
        assertDoesNotThrow(()->productRepository.saveProduct(product));
        assertDoesNotThrow(()->productRepository.saveProduct(product1));
        assertDoesNotThrow(() ->orderItemRepository.saveOrderItem(orderItem));
        assertDoesNotThrow(() ->orderItemRepository.saveOrderItem(orderItem1));

        //log.info("Ordered Items after saving -->{}",orderItemList);
        log.info("Ordered Items after saving -->{}",orderItem);
        log.info("Ordered Items after saving -->{}",orderItem1);


        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en","NG"));

        BigDecimal product1Total =orderItem.orderTotal(product);
        log.info("Item1 total is -->{}",nf.format(product1Total));

        BigDecimal product2Total=orderItem1.orderTotal(product1);
        log.info("Item2 total is -->{}",nf.format(product2Total));

        BigDecimal orderItemTotal=product1Total.add(product2Total).add(shipment.getShippingCharges());

        log.info("Order total is -->{}",nf.format(orderItemTotal));
    }

    @Test
    void testThatYouCanFindOrderedItemById(){

        orderItem=orderItemRepository.findById(1).orElse(null);

        assertNotNull(orderItem);

        log.info("Order with order 1tem 1 is -->{}",orderItem);
    }

    @Test
    void testThatYouCanFindAllOrder(){
        List<OrderItem> orderItemList=orderItemRepository.findAll();

        orderItemList.stream().forEach(System.out::println);
    }

}