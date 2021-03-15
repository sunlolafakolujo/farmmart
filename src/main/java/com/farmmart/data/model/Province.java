package com.farmmart.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String provinceCode;

    @Column(nullable = false)
    private String provinceName;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Country country;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER,mappedBy = "province",orphanRemoval = true)
    private Set<LocalGovernmentOrCounty> localGovernmentOrCounties;

    //Do i really need this method?
    public void addProvince(LocalGovernmentOrCounty localGovernmentOrCounty){
        if(localGovernmentOrCounties ==null) {
            localGovernmentOrCounties = new HashSet<>();
        }
        localGovernmentOrCounties.add(localGovernmentOrCounty);

        localGovernmentOrCounty.setProvince(this);
    }
}
