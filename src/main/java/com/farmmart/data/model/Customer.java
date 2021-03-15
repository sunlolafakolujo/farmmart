package com.farmmart.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String otherNames;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer dayOfBirth;

    @Column(nullable = false)
    private Integer monthOfBirth;

    @Column(nullable = false)
    private Integer yearOfBirth;

    @Email(message = "Invalid email address")
    @Column(nullable = false,unique = true)
    private String emailAddress;

//    @Digits(message = "Enter a valid phone", integer = 15, fraction = 0)
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private BillingDetail billingDetails;

    //To seek better understanding of @CreateTimeStamp Annotation
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime dateCustomerCreated;

    //To seek better understanding of @UpdateTimeStamp Annotation
    @Column(updatable = false)
    @UpdateTimestamp
    private LocalDateTime dateCustomerUpdated;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Set<Address> addresses;

    public void addAddress(Address address){
        if(addresses ==null){
            addresses = new HashSet<>();
        }
        addresses.add(address);

        address.setCustomer(this);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.DETACH,orphanRemoval = true)
    private Set<CustomerOrder> customerOrders;

    public void addCustomerOrder(CustomerOrder customerOrder){
        if(customerOrders==null){
            customerOrders =new HashSet<>();
        }
        customerOrders.add(customerOrder);
        customerOrder.setCustomer(this);
    }

    @ToString.Include
    public Integer age(){
        LocalDate currentYear = LocalDate.now();
        LocalDate birthday = LocalDate.of(yearOfBirth,monthOfBirth,dayOfBirth);

        if(currentYear !=null && birthday !=null){
            return Period.between(birthday,currentYear).getYears();
        }
        else
            return 0;
    }



}
