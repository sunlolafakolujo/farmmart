package com.farmmart.service.localgorvernmentorcounty;

import com.farmmart.data.exception.LocalGovernmentOrCountyException;
import com.farmmart.data.model.LocalGovernmentOrCounty;
import com.farmmart.data.repository.LocalGovernmentOrCountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalGovernmentOrCountyServiceImplementation implements LocalGovernmentOrCountyService{

    @Autowired
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;


    @Override
    public LocalGovernmentOrCounty saveLocalGovernment(LocalGovernmentOrCounty localGovernmentOrCounty) throws LocalGovernmentOrCountyException {

        if(localGovernmentOrCounty==null){
            throw new LocalGovernmentOrCountyException("LocalGovernment or County cannot be null");
        }
        else {
            return localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty);
        }
    }

    @Override
    public LocalGovernmentOrCounty findLocalGovernmentOrCountyById(Integer id) {

        return localGovernmentOrCountyRepository.findById(id).orElse(null);
    }

    @Override
    public List<LocalGovernmentOrCounty> findAllLocalGovernmentOrCounty() {
        return localGovernmentOrCountyRepository.findAll();
    }

    @Override
    public LocalGovernmentOrCounty updateLocalGovernmentOrCounty(LocalGovernmentOrCounty localGovernmentOrCounty) throws LocalGovernmentOrCountyException {

        LocalGovernmentOrCounty localGovernmentOrCounty1=localGovernmentOrCountyRepository.findById(localGovernmentOrCounty.getId()).orElse(null);

        if(localGovernmentOrCounty.getId()==null){
            throw new LocalGovernmentOrCountyException("Local Government or County does not exist");
        } else{
            if(localGovernmentOrCounty.getLocalGovernmentOrCountyCode()!=null){
                localGovernmentOrCounty1.setLocalGovernmentOrCountyCode(localGovernmentOrCounty.getLocalGovernmentOrCountyName());
            }
            if(localGovernmentOrCounty.getLocalGovernmentOrCountyName()!=null){
                localGovernmentOrCounty1.setLocalGovernmentOrCountyName(localGovernmentOrCounty.getLocalGovernmentOrCountyName());
            }
        }
        return localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty);
    }

    @Override
    public void deleteLocalGovernmentOrCountyById(Integer id) {
        localGovernmentOrCountyRepository.deleteById(id);

    }

    @Override
    public void deleteAllLocalGovernmentOrCounty() {
        localGovernmentOrCountyRepository.deleteAll();
    }
}
