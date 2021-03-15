package com.farmmart.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PaymentCardInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private String nameOnCard;

    @ToString.Exclude
    @Column(nullable = false,unique = true)
    private String paymentCardNumber;

    @ToString.Exclude
    @Column(nullable = false)
    private Integer cardExpirationMonth;

    @ToString.Exclude
    @Column(nullable = false)
    private Integer cardExpirationYear;

    @ToString.Include
    public String cardNumber(){

        return paymentMethod+" ending in "+paymentCardNumber.substring(12,16);
    }

    @ToString.Include
    public String validThru(){

        YearMonth expirationMonthAndYear =YearMonth.of(cardExpirationYear,cardExpirationMonth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        return expirationMonthAndYear.format(formatter);
    }

    @Column(nullable = false,unique = true,length = 3)
    private Integer cardCVV;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private BillingDetail billingDetails;



}
