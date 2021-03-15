package com.farmmart.service.country;

import com.farmmart.data.exception.CountryException;
import com.farmmart.data.model.Country;
import com.farmmart.data.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class CountryServiceImplementationTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryServiceImp= new CountryServiceImplementation();

    Country country;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        country=new Country();
    }

    @Test
    void testThatYouCanSaveCountryByMock(){

        when(countryRepository.save(country)).thenReturn(country);
        countryServiceImp.saveCountry(country);

        verify(countryRepository,times(1)).save(country);
    }

    @Test
    void testThatYouCanFindCountryByIdByMock(){
        when(countryRepository.findById(1)).thenReturn(Optional.of(country));
        log.info("Country with ID 1 -->{}", countryServiceImp.findCountryById(1));

        verify(countryRepository,times(1)).findById(1);
    }

    @Test
    void testThatYouCanFindAllCountryByMock(){
        List<Country> countryList= new ArrayList<>();

        when(countryRepository.findAll()).thenReturn(countryList);

        log.info("List of countries -->{}",countryServiceImp.findAllCountries());

        verify(countryRepository,times(1)).findAll();

    }

    @Test
    void testThatYouCanUpdateCountryByMock() throws CountryException {

        when(countryRepository.save(country)).thenReturn(country);

        countryServiceImp.updateCountry(1,country);

        verify(countryRepository,times(1)).save(country);


    }

    @Test
    void testThatYouCanDeleteCountryByMock(){
        doNothing().when(countryRepository).deleteById(1);
        countryServiceImp.deleteCountryById(1);

        verify(countryRepository,times(1)).deleteById(1);


    }

    @Test
    void testThatYouCanDeleteAllCountriesByMock(){
        doNothing().when(countryRepository).findAll();

        countryServiceImp.deleteAllCountries();

        verify(countryRepository,times(1)).deleteAll();
    }


}