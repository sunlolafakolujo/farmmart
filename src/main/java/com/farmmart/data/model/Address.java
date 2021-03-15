package com.farmmart.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postZipCode;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private LocalGovernmentOrCounty localGovernmentOrCounty;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses",fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Set<Customer> customers;

    public void setCustomer(Customer customer){
        if(customers == null){
            customers =new HashSet<>();
        }
        customers.add(customer);
        customer.addAddress(this);
    }
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.DETACH,mappedBy = "addresses",fetch = FetchType.EAGER)
    private Set<Supplier> suppliers;

    public void addSupplier(Supplier supplier){
        if(suppliers ==null){
            suppliers  =new HashSet<>();
        }
        suppliers.add(supplier);
        supplier.addAddress(this);
    }

    @ManyToMany(mappedBy = "addresses",fetch = FetchType.EAGER)
    private Set<BillingDetail> billingDetails;

    public void addBillingDetail(BillingDetail billingDetail){
        if(billingDetails==null){
            billingDetails =new HashSet<>();
        }

        billingDetails.add(billingDetail);
        billingDetail.addBillingAddress(this);

    }



}
