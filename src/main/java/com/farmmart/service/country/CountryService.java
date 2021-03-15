package com.farmmart.service.country;

import com.farmmart.data.exception.CountryException;
import com.farmmart.data.model.Country;

import java.util.List;

public interface CountryService {

    Country saveCountry(Country country);
    Country findCountryById(Integer id);
    List<Country> findAllCountries();
    void updateCountry(Integer id,Country country) throws CountryException;
    void deleteCountryById(Integer id);
    void deleteAllCountries();
}
