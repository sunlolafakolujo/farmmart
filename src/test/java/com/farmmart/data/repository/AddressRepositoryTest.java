package com.farmmart.data.repository;

import com.farmmart.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;
    Address address;

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;
    LocalGovernmentOrCounty localGovernmentOrCounty;

    @Autowired
    BillingDetailsRepository billingDetailsRepository;
    BillingDetail billingDetails;

    @Autowired
    PaymentCardInformationRepository paymentCardInformationRepository;
    PaymentCardInformation paymentCardInformation;

    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    @Autowired
    CountryRepository countryRepository;
    Country country;

    @Autowired
    ProvinceRepository provinceRepository;
    Province province;

    @BeforeEach
    void setUp() {
        address =new Address();
        localGovernmentOrCounty =new LocalGovernmentOrCounty();
        billingDetails = new BillingDetail();
        paymentCardInformation = new PaymentCardInformation();
        customer =new Customer();
        country = new Country();
        province = new Province();
    }

    @Test
    void testThatYouCanAddAddress(){
        country.setCountryName("Nigeria");
        country.setCountryCode("+234");

        province.setCountry(country);
        province.setProvinceName("Lagos");
        province.setProvinceCode("15");

        localGovernmentOrCounty.setProvince(province);
        localGovernmentOrCounty.setLocalGovernmentOrCountyName("Agege");
        localGovernmentOrCounty.setLocalGovernmentOrCountyCode("15.01");


        address.setLocalGovernmentOrCounty(localGovernmentOrCounty);
        address.setAddressType(AddressType.RESIDENTIAL);
        address.setCity("Mile 2");
        address.setStreetNumber("Block 202, Flat 2");
        address.setStreetName("Amuwo Odofin Housing Estate");
        address.setPostZipCode("110001");

        log.info("Address repo after before -->{}",address);

        assertDoesNotThrow(()->countryRepository.save(country));
        assertDoesNotThrow(()->provinceRepository.saveProvince(province));
        assertDoesNotThrow(()-> localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty));
        assertDoesNotThrow(() ->addressRepository.saveAddress(address));

        log.info("Address repo after saving -->{}",address);

    }

    @Test
    void testThatYouCanFindAddressById(){
        address =addressRepository.findById(1).orElse(null);

        assertNotNull(address);

        log.info("Address with ID 1 is-->{}",address);
    }

    @Test
    void testThatYouCanFindAllAddresses(){

        List<Address> addressList = new ArrayList<>();
        addressRepository.findAll().forEach(addressList::add);

        log.info("Address List -->{}",addressList);
    }

    @Test
    void testThatYouCanDeleteAddressById(){

        customerRepository.deleteById(1);
        paymentCardInformationRepository.deleteById(1);
        billingDetailsRepository.deleteById(1);
        addressRepository.deleteById(1);

        assertThat(address.getId()).isNull();

        log.info("Address ID 1 is -->{}",address.getId());
    }

    @Test
    void testThatYouCanDeleteAllAddresses(){

        addressRepository.delete(address);
    }
}