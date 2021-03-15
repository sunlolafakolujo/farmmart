package com.farmmart.service.country;

import com.farmmart.data.exception.CountryException;
import com.farmmart.data.model.Country;
import com.farmmart.data.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CountryServiceImplementation implements CountryService{


    private CountryRepository countryRepository;

    @Override
    public Country saveCountry(Country country) {

        if(country==null){
            throw new NullPointerException("Country cannot be null");
        }
        return countryRepository.save(country);
    }

    @Override
    public Country findCountryById(Integer id) {

        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Country> findAllCountries() {
        List<Country> countries=new ArrayList<>();
        countryRepository.findAll().forEach(countries::add);

        return countries;
    }

    @Override
    public void updateCountry(Integer id,Country country) throws CountryException {

        List<Country> countries=new ArrayList<>();
        for(int i =0; i <countries.size();i++){
            country =countries.get(id);
            if(country.getId().equals(id)){
                countries.set(id,country);
                return;
            }
            countryRepository.save(country);
        }

        countryRepository.save(country);
    }

    @Override
    public void deleteCountryById(Integer id) {
        countryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCountries() {
        countryRepository.deleteAll();

    }
}
