package com.farmmart.service.localgorvernmentorcounty;

import com.farmmart.data.exception.LocalGovernmentOrCountyException;
import com.farmmart.data.model.LocalGovernmentOrCounty;
import com.farmmart.data.repository.LocalGovernmentOrCountyRepository;
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
class LocalGovernmentOrCountyServiceImplementationTest {
    @Mock
    LocalGovernmentOrCountyRepository localGovernmentOrCountyRepository;

    @InjectMocks
    LocalGovernmentOrCountyService localGovernmentOrCountyService=new LocalGovernmentOrCountyServiceImplementation();

    LocalGovernmentOrCounty localGovernmentOrCounty;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        localGovernmentOrCounty=new LocalGovernmentOrCounty();
    }

    @Test
    void mockSaveLocalGovernmentOrCountyTest() throws LocalGovernmentOrCountyException {
        when(localGovernmentOrCountyRepository.saveLocalGovernmentOrCounty(localGovernmentOrCounty)).thenReturn(localGovernmentOrCounty);

        localGovernmentOrCountyService.saveLocalGovernment(localGovernmentOrCounty);
        verify(localGovernmentOrCountyRepository,times(1)).saveLocalGovernmentOrCounty(localGovernmentOrCounty);
    }

    @Test
    void mockFindLocalGovernmentOrCountyByIdTest() throws LocalGovernmentOrCountyException {
        when(localGovernmentOrCountyRepository.findById(1)).thenReturn(Optional.of(localGovernmentOrCounty));
        localGovernmentOrCountyService.findLocalGovernmentOrCountyById(1);

        verify(localGovernmentOrCountyRepository,times(1)).saveLocalGovernmentOrCounty(localGovernmentOrCounty);
    }

    @Test
    void mockFindAllLocalGovernmentOrCountyTest(){
        List<LocalGovernmentOrCounty> localGovernmentOrCountyList = new ArrayList<>();
        when(localGovernmentOrCountyRepository.findAll()).thenReturn(localGovernmentOrCountyList);
        localGovernmentOrCountyService.findAllLocalGovernmentOrCounty();

        verify(localGovernmentOrCountyRepository,times(1)).findAll();
    }

    @Test
    void mockUpdateLocalGovernmentOrCountyTest(){

    }

    @Test
    void mockDeleteLocalGovernmentOrCountyByIdTest(){
        doNothing().when(localGovernmentOrCountyRepository).deleteById(1);

        localGovernmentOrCountyService.deleteLocalGovernmentOrCountyById(1);

        verify(localGovernmentOrCountyRepository,times(1)).deleteById(1);
    }

    @Test
    void mockDeleteAllLocalGovernmentOrCountyTest(){
        doNothing().when(localGovernmentOrCountyRepository).deleteAll();

        localGovernmentOrCountyService.deleteAllLocalGovernmentOrCounty();

        verify(localGovernmentOrCountyRepository,times(1)).deleteAll();
    }
}