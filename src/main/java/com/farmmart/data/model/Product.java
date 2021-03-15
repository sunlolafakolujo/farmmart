package com.farmmart.data.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false, length = 600)
    private String productDescription;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    ProductCondition productCondition;

    @Enumerated(EnumType.STRING)
    private ProductSize productSize;

    private String productColour;

    private Double productWeight;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure productUnitOfMeasure;

    private String productStyle;

    private String productDimension;

    private String productManufacturer;

    private String productModel;

    @Positive(message = "Product price cannot be negative or zero")
    private BigDecimal productPrice;

    @Positive(message = "Product stock quantity must be greater than zero")
    private BigDecimal productStockQuantity;

    @ManyToMany(mappedBy = "products",cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Set<Supplier> suppliers;

    public void setSupplier(Supplier supplier){
        if(suppliers == null){
            suppliers = new HashSet<>();
        }
            suppliers.add(supplier);
    }

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER,mappedBy = "products")
    private Set<OrderItem> orderItems=new HashSet<>();


    public BigDecimal productBalance(Product product,OrderItem orderItem){
        return product.getProductStockQuantity().subtract(orderItem.getOrderQuantity());
    }


}
