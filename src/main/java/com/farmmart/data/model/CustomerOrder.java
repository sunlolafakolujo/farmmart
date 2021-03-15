package com.farmmart.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime orderDate;

    @Column(updatable = true)
    private Date shippingDate;

    @Column(updatable = true)
    private Date deliveryDate;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH,mappedBy = "customerOrder")//to review the relationship(customer order can be consolidated depending on the commodity)
    private Set<Shipment> shipments;

    public void setShipment(Shipment shipment){
        if(shipments==null){
            shipments=new HashSet<>();
        }

        shipments.add(shipment);
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Customer customer;

    @ToString.Exclude
    @OneToMany(mappedBy = "customerOrder",fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Set<OrderItem> orderItems;

    public void setOrderItem(OrderItem orderItem){
        if(orderItems ==null){
            orderItems = new HashSet<>();
        }
        orderItems.add(orderItem);
    }


}
