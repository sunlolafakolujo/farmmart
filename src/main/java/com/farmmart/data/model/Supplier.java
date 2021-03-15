package com.farmmart.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String rcNumber;

    @Column(nullable = false)
    private String taxIdentificationNumber;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String phoneNumber;

    @Email(message ="Invalid email address")
    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 3000)
    private String natureOfBusiness;

    @Enumerated(EnumType.STRING)
    private SupplierCategory supplierCategory;

    @Enumerated(EnumType.STRING)
    private FacilityType facility;

    @CreationTimestamp
    private LocalDateTime dateSupplierCreated;

    @UpdateTimestamp
    private LocalDateTime dateSupplierUpdated;

    //@ToString.Exclude
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Set<Address> addresses;

    public void addAddress(Address address){
        if(addresses ==null){
            addresses =new HashSet<>();
        }
        addresses.add(address);
    }

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product){
        if(products==null)
            products.add(product);
    }

}
