package com.farmmart.data.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Positive(message = "Order product quantity cannot be zero or less")
    @Column(nullable = false)
    private BigDecimal orderQuantity;

    @Column(nullable = false)
    private Date actualDeliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @DecimalMax("1")
    @Positive(message = "Value cannot be zero but less than 1")
    @Column(nullable = false)
    private BigDecimal valueAddedTax;

    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private CustomerOrder customerOrder;

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Set<Product> products;

    public void setProduct(Product product){
        if(products== null){
            products = new HashSet<>();
        }
        products.add(product);
    }

    public BigDecimal orderTotal(Product product){

        return (product.getProductPrice().multiply(orderQuantity))
                .add(product.getProductPrice().multiply(orderQuantity).multiply(valueAddedTax));
    }

}
