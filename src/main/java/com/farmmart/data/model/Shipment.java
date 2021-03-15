package com.farmmart.data.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ShippingMode shippingMode;

    @Positive(message = "Shipping charges cannot be negative or zero")
    private BigDecimal shippingCharges;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private CustomerOrder customerOrder;

}
