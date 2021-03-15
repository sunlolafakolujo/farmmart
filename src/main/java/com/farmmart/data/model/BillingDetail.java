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
public class BillingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private Set<Address> addresses;

    public void addBillingAddress(Address billingAddress){
        if(addresses ==null){
            addresses = new HashSet<>();
        }
        addresses.add(billingAddress);
    }

    @OneToMany(mappedBy = "billingDetails",cascade = CascadeType.DETACH,fetch = FetchType.EAGER,orphanRemoval = true)
    private Set<PaymentCardInformation> paymentCardInformationSet;


    private void setPaymentCardInformation(PaymentCardInformation paymentCardInformation){
        if(paymentCardInformationSet == null){
            paymentCardInformationSet = new HashSet<>();
        }
            paymentCardInformationSet.add(paymentCardInformation);
    }



}
