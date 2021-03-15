package com.farmmart.data.repository;

import com.farmmart.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class LocalGovernmentOrCountyRepositoryTest {

    @Autowired
    ProvinceRepository provinceRepository;
    Province province;

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;
    LocalGovernmentOrCounty localGovernmentOrCounty;

    @Autowired
    AddressRepository addressRepository;
    Address address;

    @Autowired
    CountryRepository countryRepository;
    Country country;
    @BeforeEach
    void setUp() {

        province = new Province();
        localGovernmentOrCounty = new LocalGovernmentOrCounty();
        address = new Address();
        country=new Country();
    }

    @Test
    void testThatYouCanSaveLocalGovernmentOrCounty(){
        province = provinceRepository.findById(1).orElse(null);

        localGovernmentOrCounty.setLocalGovernmentOrCountyCode("15.01");
        localGovernmentOrCounty.setLocalGovernmentOrCountyName("Agege");
        localGovernmentOrCounty.setProvince(province);

        log.info("Local Government or County repo before saving -->{}",localGovernmentOrCounty);
        assertDoesNotThrow(() ->provinceRepository.saveProvince(province));
        assertDoesNotThrow(() ->localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty));

        assertThat(localGovernmentOrCounty.getId()).isNotNull();

        log.info("Local Government or County repo before saving -->{}",localGovernmentOrCounty);
    }

    @Test
    void testThatYouCanAddAddressesToLocalGovernmentOrCounty(){
        address.setAddressType(AddressType.RESIDENTIAL);
        address.setStreetNumber("2A");
        address.setCity("Ikeja");
        address.setStreetName("Agbeluyi Street, Onigbongbo");
        address.setPostZipCode("110001");

        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);

        country.setCountryCode("+234");
        country.setCountryName("Nigeria");


        province.setProvinceCode("15");
        province.setProvinceName("Lagos");
        province.setCountry(country);

        localGovernmentOrCounty.setAddresses(addressSet);
        localGovernmentOrCounty.setLocalGovernmentOrCountyCode("15.07");
        localGovernmentOrCounty.setLocalGovernmentOrCountyName("Ikeja");
        localGovernmentOrCounty.setProvince(province);

        log.info("Local Government repo before saving -->{}",localGovernmentOrCounty);

        assertDoesNotThrow(()->countryRepository.save(country));
        assertDoesNotThrow(()->provinceRepository.saveProvince(province));
        assertDoesNotThrow(() ->localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty));

        log.info("Local Government repo after saving -->{}",localGovernmentOrCounty);


    }

    @Test
    void testThatYouCanFindLocalGovernmentOrCountyById(){
        localGovernmentOrCounty = localGovernmentOrCountyRepository.findById(1).orElse(null);
        assertNotNull(localGovernmentOrCounty);
        log.info("Local Government or County with Id 1 -->{}",localGovernmentOrCounty);
    }

    @Test
    void testThatYouCanFindAllLocalGovernmentOrCounty(){
        List<LocalGovernmentOrCounty> localGovernmentOrCountyList =localGovernmentOrCountyRepository.findAll();

        log.info("Local Government or County list -->{}",localGovernmentOrCountyList);
    }

    @Test
    void testThatYouCanUpdateLocalGovernmentOrCounty(){
        localGovernmentOrCounty =localGovernmentOrCountyRepository.findById(3).orElse(null);

        log.info("Local Government or County Id 3 before update -->{}",localGovernmentOrCounty);

        localGovernmentOrCounty.setLocalGovernmentOrCountyCode("15.12");
        localGovernmentOrCounty.setLocalGovernmentOrCountyName("Ikorodu");

        assertDoesNotThrow(() -> localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty));

        assertThat(localGovernmentOrCounty.getLocalGovernmentOrCountyCode()).isEqualTo("15.12");
        assertThat(localGovernmentOrCounty.getLocalGovernmentOrCountyName()).isEqualTo("Ikorodu");
        log.info("Local Government or County Id 3 after update -->{}",localGovernmentOrCounty);

    }

    @Test
    void testThatYouCanDeleteLocalGovernmentOrCountyById(){

        localGovernmentOrCountyRepository.deleteById(3);
        assertThat(localGovernmentOrCounty.getId()).isNull();

        log.info("Local Government or county Id 3 is -->{}",localGovernmentOrCounty.getId());

    }

    @Test
    void testThatYouCanDeleteAllLocalGovernmentOrCounty(){

        localGovernmentOrCountyRepository.delete(localGovernmentOrCounty);

        assertNotNull(localGovernmentOrCounty);
        log.info("Local Government or County repo is -->{}",localGovernmentOrCounty);

    }
}