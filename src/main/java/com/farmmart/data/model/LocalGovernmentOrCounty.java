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
public class LocalGovernmentOrCounty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String localGovernmentOrCountyCode;

    @Column(nullable = false)
    private String localGovernmentOrCountyName;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Province province;

    @OneToMany(mappedBy = "localGovernmentOrCounty",fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Address> addresses;

    private void setAddress(Address address){
        if(addresses == null) {
            addresses =new HashSet<>();
        }
        addresses.add(address);

        address.setLocalGovernmentOrCounty(this);
    }
}
