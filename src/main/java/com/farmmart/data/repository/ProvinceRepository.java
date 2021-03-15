package com.farmmart.data.repository;

import com.farmmart.data.exception.ProvinceException;
import com.farmmart.data.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {


    public default Province saveProvince(Province province) throws ProvinceException {
        if(!isProvinceValid(province)){
            throw new ProvinceException("Country cannot be null");
        }
        return save(province);
    }

    private boolean isProvinceValid(Province province){
        if(province.getCountry() == null){
            return  false;
        }else
            return true;
    }
}
