package com.farmmart.service.province;

import com.farmmart.data.exception.ProvinceException;
import com.farmmart.data.model.Province;
import com.farmmart.data.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProvinceServiceImplementation implements ProvinceService{

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public Province saveProvince(Province province) throws ProvinceException {
        if(province == null){
            throw new NullPointerException("Province cannot be null");
        }
        else {
            return provinceRepository.saveProvince(province);
        }
    }

    @Override
    public Province findProvinceById(Integer id) {

        return provinceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Province> findAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        provinceRepository.findAll().forEach(provinces::add);
        return provinces;
    }

    @Override
    public void updateProvince(Integer id,Province province) throws ProvinceException {
        Province saveProvince=provinceRepository.findById(province.getId()).orElse(null);

        if(province.getProvinceCode()!=null){
            saveProvince.setProvinceCode(province.getProvinceCode());
        }
        else if(province.getProvinceName()!=null){
            saveProvince.setProvinceName(province.getProvinceName());
        }
        else{
            System.out.println("Province id does not exist");
        }
         provinceRepository.saveProvince(province);
    }

    @Override
    public void deleteProvinceById(Integer id) {
        provinceRepository.deleteById(id);

    }

    @Override
    public void deleteAllProvinces() {
        provinceRepository.deleteAll();

    }
}
