package com.farmmart.data.repository;

import com.farmmart.data.model.Country;
import com.farmmart.data.model.Province;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class ProvinceRepositoryTest {
    @Autowired
    CountryRepository countryRepository;
    Country country;

    @Autowired
    ProvinceRepository provinceRepository;
    Province province;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    BillingDetailsRepository billingDetailsRepository;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        country = new Country();
        province = new Province();
    }

    @Test
    void testThatYouCanSaveProvince(){

        country = countryRepository.findById(1).orElse(null);
        province.setCountry(country);
        province.setProvinceCode("17");
        province.setProvinceName("Ogun");

        log.info("Province repo before saving -->{}",province);

        assertDoesNotThrow(() ->provinceRepository.saveProvince(province));

        assertThat(province.getId()).isNotNull();

        log.info("Province repo after saving -->{}",province);

    }

    @Test
    void testThatYouCanFindProvinceById(){

        province = provinceRepository.findById(1).orElse(null);

        assertThat(province.getId()).isNotNull();

        log.info("Name of province id 1 -->{}",province.getProvinceName());
        log.info("Code of province id 1 -->{}",province.getProvinceCode());
        log.info("Country of province id 1 -->{}",province.getCountry());
    }

    @Test
    void testThatYouCanFindAllProvince(){

        List<Province> provinceList = provinceRepository.findAll();

        log.info("List of provinces: -->{}",provinceList);

    }


    @Test
    void testThatYouCanUpdateProvince(){
        province = provinceRepository.findById(7).orElse(null);

        province.setProvinceName("Kaduna");
        province.setProvinceCode("07");

        assertThat(province.getProvinceCode()).isEqualTo("07");
        assertThat(province.getProvinceName()).isEqualTo("Kaduna");

        log.info("Province code for Updated Province id 7 -->{}",province.getProvinceCode());
        log.info("Province name for Updated Province id 7 -->{}",province.getProvinceName());

        assertDoesNotThrow(() -> provinceRepository.saveProvince(province));
    }

    @Test
    void testThatYouCanDeleteProvinceById(){
        provinceRepository.deleteById(7);

        assertThat(province.getId()).isNull();

        log.info("Province Id 7 is -->{}",province.getId());
    }

    @Test
    void testThatYouCanDeleteAllProvinces(){
        customerRepository.deleteAll();
        billingDetailsRepository.deleteAll();
        supplierRepository.deleteAll();
        addressRepository.deleteAll();
        provinceRepository.deleteAll();
        log.info("Province is -->{}",province);
    }

}