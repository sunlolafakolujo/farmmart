package com.farmmart.data.repository;

import com.farmmart.data.exception.LocalGovernmentOrCountyException;
import com.farmmart.data.model.LocalGovernmentOrCounty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalGovernmentOrCountyRepository extends JpaRepository<LocalGovernmentOrCounty,Integer> {

    public default LocalGovernmentOrCounty saveLocalGovernmentOrCounty(LocalGovernmentOrCounty localGovernmentOrCounty) throws LocalGovernmentOrCountyException {
        if(!isValidLocalGovernmentOrCounty(localGovernmentOrCounty)){
            throw  new LocalGovernmentOrCountyException("Province cannot be null");
        }
        return save(localGovernmentOrCounty);
    }

    private boolean isValidLocalGovernmentOrCounty(LocalGovernmentOrCounty localGovernmentOrCounty){
        if(localGovernmentOrCounty.getProvince()==null){
            return false;
        }else
            return true;
    }
}
