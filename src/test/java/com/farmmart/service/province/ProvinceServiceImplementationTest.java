package com.farmmart.service.province;

import com.farmmart.data.exception.ProvinceException;
import com.farmmart.data.model.Province;
import com.farmmart.data.repository.ProvinceRepository;
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
class ProvinceServiceImplementationTest {

    @Mock
    ProvinceRepository provinceRepository;

    @InjectMocks
    ProvinceService provinceService = new ProvinceServiceImplementation();

    Province province;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        province=new Province();
    }

    @Test
    void testThatYouCanSaveProvinceByMock() throws ProvinceException {

        when(provinceRepository.saveProvince(province)).thenReturn(province);
        provinceService.saveProvince(province);

        verify(provinceRepository,times(1)).saveProvince(province);
    }

    @Test
    void testThatYouCanFindProvinceByIdMock() throws ProvinceException {

        when(provinceRepository.findById(1)).thenReturn(Optional.of(province));
        provinceService.findProvinceById(1);

        verify(provinceRepository,times(1)).saveProvince(province);
    }

    @Test
    void testThatYouFindAllProvincesByMock() throws ProvinceException {

        List<Province> provinceList =new ArrayList<>();

        when(provinceRepository.findAll()).thenReturn(provinceList);
        provinceService.findAllProvinces();

        verify(provinceRepository,times(1)).saveProvince(province);
    }

    @Test
    void testThatYouCanUpdateProvinceByMock(){

    }

    @Test
    void testThatYouCanDeleteProvinceByIdMock() throws ProvinceException {
        doNothing().when(provinceRepository).deleteById(1);

        provinceService.deleteProvinceById(1);

        verify(provinceRepository,times(1)).deleteById(1);

    }

    @Test
    void testThatYouCanDeleteAllProvincesByMock(){
        doNothing().when(provinceRepository).deleteAll();

        provinceService.deleteAllProvinces();

        verify(provinceRepository,times(1)).deleteAll();
    }


}