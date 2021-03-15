package com.farmmart.data.repository;

import com.farmmart.data.model.Country;
import com.farmmart.data.model.Province;
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
@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;
    Country country;

    @Autowired
    ProvinceRepository provinceRepository;
    Province province;

    @BeforeEach
    void setUp() {
        country = new Country();
        province = new Province();
    }

    @Test
    void testThatYouCanSaveCountry(){

        country.setCountryCode("+44");
        country.setCountryName("Great Britain");


        log.info("Country repo before saving -->{}",country);

        assertDoesNotThrow(() ->countryRepository.save(country));

        assertThat(country.getId()).isNotNull();
        log.info("Country repo after saving -->{}",country);
    }

    @Test
    void testThatYouCanFindCountryById(){
        country = countryRepository.findById(5).orElse(null);
        assertThat(country.getId()).isNotNull();
        log.info("Country with id 5 is: -->{}",country);

    }

    @Test
    void testThatYouCanFindAllCountry(){
        List<Country> countryList = new ArrayList<>();
        countryRepository.findAll().forEach(countryList::add);

        log.info("List of registered Countries: -->{}",countryList);

    }

    @Test
    void testThatYouCanFindAllProvinceInACountryById(){
        country =countryRepository.findById(1).orElse(null);

        log.info("Provinces in Country Id 1 are: -->{}",country);
    }

    @Test
    void testThatYouCanUpdateCountry(){
        country = countryRepository.findById(6).orElse(null);

        country.setCountryCode("+86");
        country.setCountryName("Republic of China");

        assertDoesNotThrow(() -> countryRepository.save(country));
        assertThat(country.getCountryCode()).isEqualTo("+86");
        assertThat(country.getCountryName()).isEqualTo("Republic of China");
        log.info("Country with id 6 is: -->{}",country);
    }

    @Test
    void testThatYouCanDeleteCountryById(){
        countryRepository.deleteById(6);
        assertThat(country.getId()).isNull();

        country = countryRepository.findById(6).orElse(null);
        log.info("Country with id 6 is: -->{}",country);

    }

    @Test
    void testThatYouCanDeleteAllCountry(){
        countryRepository.deleteAll();
        log.info("Country repo is: -->{}",country);
    }
}