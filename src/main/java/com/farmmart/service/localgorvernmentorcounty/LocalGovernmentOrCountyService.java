package com.farmmart.service.localgorvernmentorcounty;

import com.farmmart.data.exception.LocalGovernmentOrCountyException;
import com.farmmart.data.model.LocalGovernmentOrCounty;

import java.util.List;

public interface LocalGovernmentOrCountyService {

    public LocalGovernmentOrCounty saveLocalGovernment(LocalGovernmentOrCounty localGovernmentOrCounty) throws LocalGovernmentOrCountyException;
    public LocalGovernmentOrCounty findLocalGovernmentOrCountyById(Integer id);
    public List<LocalGovernmentOrCounty> findAllLocalGovernmentOrCounty();
    public LocalGovernmentOrCounty updateLocalGovernmentOrCounty(LocalGovernmentOrCounty localGovernmentOrCounty) throws LocalGovernmentOrCountyException;
    public void deleteLocalGovernmentOrCountyById(Integer id);
    public void deleteAllLocalGovernmentOrCounty();


}
