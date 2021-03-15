package com.farmmart.service.province;

import com.farmmart.data.exception.ProvinceException;
import com.farmmart.data.model.Province;

import java.util.List;

public interface ProvinceService {

    public Province saveProvince(Province province) throws ProvinceException;
    public Province findProvinceById(Integer id);
    public List<Province> findAllProvinces();
    public void updateProvince(Integer id,Province province) throws ProvinceException;
    public void deleteProvinceById(Integer id);
    public void deleteAllProvinces();
}
